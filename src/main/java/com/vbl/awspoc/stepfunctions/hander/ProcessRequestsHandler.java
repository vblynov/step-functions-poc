package com.vbl.awspoc.stepfunctions.hander;

import com.amazonaws.services.lambda.runtime.Context;
import com.vbl.awspoc.stepfunctions.request.DownloadRequest;

public class ProcessRequestsHandler {

    public void handleRequest(DownloadRequest downloadRequest, Context context) {
        var logger = context.getLogger();
        logger.log("Start processing request with params " + downloadRequest.getParameters1());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.log("Finished processing request with params " + downloadRequest.getParameters1());
    }

}
