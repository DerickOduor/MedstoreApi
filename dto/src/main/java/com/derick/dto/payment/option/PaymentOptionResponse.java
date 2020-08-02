package com.derick.dto.payment.option;

import java.util.List;

public class PaymentOptionResponse {
    private String Response;
    private PaymentOptionDto paymentOptionDto;
    private List<PaymentOptionDto> paymentOptionDtos;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public PaymentOptionDto getPaymentOptionDto() {
        return paymentOptionDto;
    }

    public void setPaymentOptionDto(PaymentOptionDto paymentOptionDto) {
        this.paymentOptionDto = paymentOptionDto;
    }

    public List<PaymentOptionDto> getPaymentOptionDtos() {
        return paymentOptionDtos;
    }

    public void setPaymentOptionDtos(List<PaymentOptionDto> paymentOptionDtos) {
        this.paymentOptionDtos = paymentOptionDtos;
    }
}
