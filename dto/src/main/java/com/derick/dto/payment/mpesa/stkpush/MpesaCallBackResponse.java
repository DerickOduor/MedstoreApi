package com.derick.dto.payment.mpesa.stkpush;

public class MpesaCallBackResponse {
    public Body Body;

    public com.derick.dto.payment.mpesa.stkpush.Body getBody() {
        return Body;
    }

    public void setBody(com.derick.dto.payment.mpesa.stkpush.Body body) {
        Body = body;
    }
}
