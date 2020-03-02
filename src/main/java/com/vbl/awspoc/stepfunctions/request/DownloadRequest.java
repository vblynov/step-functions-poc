package com.vbl.awspoc.stepfunctions.request;

public class DownloadRequest {

    private int requestNumber;
    private String requestUrl;
    private String parameters1;
    private String parameters2;
    private String parameters3;
    private String parameters4;
    private String parameters5;
    private String parameters6;
    private String parameters7;

    public DownloadRequest() {

    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getParameters1() {
        return parameters1;
    }

    public void setParameters1(String parameters1) {
        this.parameters1 = parameters1;
    }

    public String getParameters2() {
        return parameters2;
    }

    public void setParameters2(String parameters2) {
        this.parameters2 = parameters2;
    }

    public String getParameters3() {
        return parameters3;
    }

    public void setParameters3(String parameters3) {
        this.parameters3 = parameters3;
    }

    public String getParameters4() {
        return parameters4;
    }

    public void setParameters4(String parameters4) {
        this.parameters4 = parameters4;
    }

    public String getParameters5() {
        return parameters5;
    }

    public void setParameters5(String parameters5) {
        this.parameters5 = parameters5;
    }

    public String getParameters6() {
        return parameters6;
    }

    public void setParameters6(String parameters6) {
        this.parameters6 = parameters6;
    }

    public String getParameters7() {
        return parameters7;
    }

    public void setParameters7(String parameters7) {
        this.parameters7 = parameters7;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }
}