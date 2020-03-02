package com.vbl.awspoc.stepfunctions.hander;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vbl.awspoc.stepfunctions.request.DownloadRequest;


public class CreateRequestsHandler {

    public void handleRequest(DownloadRequest request) {
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < request.getRequestNumber(); i++) {
            request.setParameters1("parameter1Value" + i);
            request.setParameters2("parameter22222222222Value");
            request.setParameters3("parameter333Value");
            request.setParameters4("parameter4444444444444Value");
            request.setParameters5("parameter555555555555555Value");
            request.setParameters6("parameter66666666666666Value");
            request.setParameters7("parameter777777777777LongValue");

            String serializedRequest;
            try {
                serializedRequest = mapper.writeValueAsString(request);
            } catch (JsonProcessingException ex) {
                throw new IllegalArgumentException(ex);
            }

            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl("https://sqs.us-east-1.amazonaws.com/085393336907/download-requests-queue")
                    .withMessageBody(serializedRequest);
            sqs.sendMessage(sendMessageRequest);
        }
    }
}
