package com.vbl.awspoc.stepfunctions.request;

public class DownloadRequest {

    private String requestUrl;
    private String parameters;

    public DownloadRequest() {

    }

    public DownloadRequest(String requestUrl, String parameters) {
        this.requestUrl = requestUrl;
        this.parameters = parameters;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getParameters() {
        return parameters;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}