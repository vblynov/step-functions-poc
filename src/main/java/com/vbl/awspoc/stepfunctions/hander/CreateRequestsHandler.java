package com.vbl.awspoc.stepfunctions.hander;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.vbl.awspoc.stepfunctions.request.DownloadRequest;

import java.util.ArrayList;
import java.util.List;

public class CreateRequestsHandler implements RequestHandler<Object, List<DownloadRequest>> {

    public List<DownloadRequest> handleRequest(Object o, Context context) {
        var logger = context.getLogger();
        logger.log("Start creating new DownloadRequest");
        var generatedRequests = new ArrayList<DownloadRequest>(100);
        for (int i = 0; i < 100; i++) {
            generatedRequests.add(new DownloadRequest("http://sample.com", Integer.toString(i)));
        }
        logger.log("Finished");
        return generatedRequests;
    }
}
