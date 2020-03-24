package com.vbl.awspoc.stepfunctions.hander;

import com.amazonaws.services.lambda.runtime.Context;
import com.vbl.awspoc.stepfunctions.exceptions.RequestProcessorException;
import com.vbl.awspoc.stepfunctions.request.DownloadRequest;
import com.vbl.awspoc.stepfunctions.request.ProcessResult;

public class ProcessRequestsHandler {

    public ProcessResult handleRequest(DownloadRequest downloadRequest, Context context) {
        var logger = context.getLogger();
        var needThrow = Boolean.parseBoolean(System.getenv("THROW"));
        logger.log("Start processing request with params " + downloadRequest.getParameters1());
        try {
            if (needThrow && "parameter1Value1".equals(downloadRequest.getParameters1())) {
                throw new RequestProcessorException();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.log("Finished processing request with params " + downloadRequest.getParameters1());
        var result = new ProcessResult();
        result.setFailedRecords(0);
        result.setProcessedRecords(5);
        return result;
    }

}
