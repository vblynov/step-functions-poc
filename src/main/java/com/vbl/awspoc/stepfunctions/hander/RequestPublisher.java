package com.vbl.awspoc.stepfunctions.hander;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vbl.awspoc.stepfunctions.request.DownloadRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestPublisher {

    public Object publishRequests() {
        //TODO pass batch size as a parameter?
        final int batchSize = 5;
        final String queueUrl = "https://sqs.us-east-1.amazonaws.com/085393336907/download-requests-queue";

        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        List<DownloadRequest> requests = new ArrayList<>(batchSize);
        ObjectMapper mapper = new ObjectMapper();
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
        return requests.isEmpty() ? "No data" : requests;

    }

}
