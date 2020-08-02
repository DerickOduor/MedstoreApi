package com.derick.external.payment.mpesa.c2b;

import org.springframework.stereotype.Component;

import java.util.List;

public class StkPushResponse {
    Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    class Body{
        StkCallback stkCallback;

        public StkCallback getStkCallback() {
            return stkCallback;
        }

        public void setStkCallback(StkCallback stkCallback) {
            this.stkCallback = stkCallback;
        }

        class StkCallback{
            String MerchantRequestID;
            String CheckoutRequestID;
            String ResultDesc;
            int ResultCode;
            CallbackMetadata CallbackMetadata;

            class CallbackMetadata{
                List<CallbackMetadataItem> Item;

                public List<CallbackMetadataItem> getItem() {
                    return Item;
                }

                public void setItem(List<CallbackMetadataItem> item) {
                    Item = item;
                }

                class CallbackMetadataItem{
                    String Name;
                    String Value;
                }
            }

            public StkCallback.CallbackMetadata getCallbackMetadata() {
                return CallbackMetadata;
            }

            public void setCallbackMetadata(StkCallback.CallbackMetadata callbackMetadata) {
                CallbackMetadata = callbackMetadata;
            }

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
        }
    }
}
