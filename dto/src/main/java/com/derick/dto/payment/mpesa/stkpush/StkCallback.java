package com.derick.dto.payment.mpesa.stkpush;

public class StkCallback {
    public String MerchantRequestID ;
    public String CheckoutRequestID ;
    public int ResultCode ;
    public String ResultDesc ;
    public CallbackMetadata CallbackMetadata ;

    public String getMerchantRequestID() {
        return MerchantRequestID;
    }

    public void setMerchantRequestID(String merchantRequestID) {
        MerchantRequestID = merchantRequestID;
    }

    public String getCheckoutRequestID() {
        return CheckoutRequestID;
    }

    public void setCheckoutRequestID(String checkoutRequestID) {
        CheckoutRequestID = checkoutRequestID;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public String getResultDesc() {
        return ResultDesc;
    }

    public void setResultDesc(String resultDesc) {
        ResultDesc = resultDesc;
    }

    public com.derick.dto.payment.mpesa.stkpush.CallbackMetadata getCallbackMetadata() {
        return CallbackMetadata;
    }

    public void setCallbackMetadata(com.derick.dto.payment.mpesa.stkpush.CallbackMetadata callbackMetadata) {
        CallbackMetadata = callbackMetadata;
    }
}
