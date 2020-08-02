package com.derick.external.payment.mpesa.response;

public class ValidateResponse {

    String ResultDesc;
    int ResultCode;
    String ThirdPartyTransID;

    public String getResultDesc() {
        return ResultDesc;
    }

    public void setResultDesc(String resultDesc) {
        ResultDesc = resultDesc;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public String getThirdPartyTransID() {
        return ThirdPartyTransID;
    }

    public void setThirdPartyTransID(String thirdPartyTransID) {
        ThirdPartyTransID = thirdPartyTransID;
    }
}
