package com.derick.dto.payment.mpesa.stkpush;

import java.util.List;

public class CallbackMetadata {
    public List<Item> Item;

    public List<com.derick.dto.payment.mpesa.stkpush.Item> getItem() {
        return Item;
    }

    public void setItem(List<com.derick.dto.payment.mpesa.stkpush.Item> item) {
        Item = item;
    }
}
