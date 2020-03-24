package com.vbl.awspoc.stepfunctions.hander;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vbl.awspoc.stepfunctions.request.DownloadRequest;
import com.vbl.awspoc.stepfunctions.request.DownloadRequestHolder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestPublisher implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        final int batchSize = 5;
        final String queueUrl = "https://sqs.us-east-1.amazonaws.com/085393336907/download-requests-queue";

        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        List<DownloadRequest> requests = new ArrayList<>(batchSize);
        boolean isEmpty = false;
        while (requests.size() < batchSize && !isEmpty) {
            var receiveRequest = new ReceiveMessageRequest();
            receiveRequest.setQueueUrl(queueUrl);
            receiveRequest.setMaxNumberOfMessages(Math.min(10, batchSize - requests.size()));
            receiveRequest.setWaitTimeSeconds(5);

            List<DownloadRequest> receivedRequests = sqs.receiveMessage(receiveRequest).getMessages()
                    .stream()
                    .peek(message -> sqs.deleteMessage(queueUrl, message.getReceiptHandle()))
                    .map(message -> {
                        try {
                            return mapper.readValue(message.getBody(), DownloadRequest.class);
                        } catch (Exception ex) {
                            throw new IllegalArgumentException(ex);
                        }
                    }).collect(Collectors.toList());
            requests.addAll(receivedRequests);
            isEmpty = receivedRequests.isEmpty();
        }
        var requestHolder = new DownloadRequestHolder();
        requestHolder.setProcessedRecords(10);
        if (requests.isEmpty()) {
            requestHolder.setRequests("No data");
        } else {
            requestHolder.setRequests(requests);
        }
        mapper.writeValue(outputStream, requests);
    }
}
