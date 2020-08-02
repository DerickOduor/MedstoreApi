package com.derick.service;

import com.derick.domain.StkPushRequest;
import com.derick.dto.payment.mpesa.stkpush.MpesaCallBackResponse;

public interface IStkPushService {
    public void addStkPushRequest(StkPushRequest stkPushRequest);
    public void addStkPushResponse(MpesaCallBackResponse mpesaCallBackResponse);
}
