package com.derick.dto.payment.mpesa.stkpush;

public class Body {
    public StkCallback stkCallback;

    public StkCallback getStkCallback() {
        return stkCallback;
    }

    public void setStkCallback(StkCallback stkCallback) {
        this.stkCallback = stkCallback;
    }
}
