package com.derick.dto.quotation;

import java.util.List;

public class QuotationResponse {
    private String Response;
    private QuotationDto quotation;
    private List<QuotationDto> quotations;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public QuotationDto getQuotation() {
        return quotation;
    }

    public void setQuotation(QuotationDto quotation) {
        this.quotation = quotation;
    }

    public List<QuotationDto> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<QuotationDto> quotations) {
        this.quotations = quotations;
    }
}
