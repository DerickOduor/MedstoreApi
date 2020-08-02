package com.derick.external.payment.mpesa.response;

public class QueueTimeoutResponse {
    private String ResponseCode;
    private String ResponseDesc;

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseDesc() {
        return ResponseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        ResponseDesc = responseDesc;
    }
}
