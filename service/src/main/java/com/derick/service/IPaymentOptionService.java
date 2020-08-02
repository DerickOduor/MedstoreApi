package com.derick.service;

import com.derick.dto.payment.option.PaymentOptionDto;
import com.derick.dto.payment.option.PaymentOptionResponse;

public interface IPaymentOptionService {
    public PaymentOptionResponse getPaymentoptions();
    public PaymentOptionResponse getPaymentoptions(boolean Status);
    public PaymentOptionResponse getPaymentoption(int id);
    public PaymentOptionResponse deletePaymentoption(int id);
    public PaymentOptionResponse addPaymentoption(PaymentOptionDto paymentOptionDto);
    public PaymentOptionResponse updatePaymentoption(PaymentOptionDto paymentOptionDto);
}
