package com.derick.service;

import com.derick.dto.quotation.AddQuotationDto;
import com.derick.dto.quotation.QuotationDto;
import com.derick.dto.quotation.QuotationResponse;

public interface IPrescriptionQuotationService {
    public QuotationResponse sendQuotation(AddQuotationDto quotationDto) throws Exception;
    public QuotationResponse updateQuotation(QuotationDto quotationDto) throws Exception;
    public QuotationResponse viewQuotation(int QuotationId) throws Exception;
    public QuotationResponse viewCustomerQuotations(int CustomerId) throws Exception;
    public QuotationResponse deleteQuotation(int QuotationId) throws Exception;
    public QuotationResponse deleteCustomerQuotations(int CustomerId) throws Exception;
}
