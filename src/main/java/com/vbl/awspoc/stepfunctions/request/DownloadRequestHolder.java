package com.vbl.awspoc.stepfunctions.request;

import java.util.List;

public class DownloadRequestHolder {
    private Object requests;
    private int processedRecords;
    private int failedRecords;

    public Object getRequests() {
        return requests;
    }

    public void setRequests(Object requests) {
        this.requests = requests;
    }

    public int getProcessedRecords() {
        return processedRecords;
    }

    public void setProcessedRecords(int processedRecords) {
        this.processedRecords = processedRecords;
    }

    public int getFailedRecords() {
        return failedRecords;
    }

    public void setFailedRecords(int failedRecords) {
        this.failedRecords = failedRecords;
    }
}
