package com.derick.external.payment.mpesa.response;

public class Validation {
    String TransactionType;//":"",
    String TransID;//":"LGR219G3EY",
    String TransTime;//":"20170727104247",
    String TransAmount;//":"10.00",
    String BusinessShortCode;//":"600134",
    String BillRefNumber;//":"xyz",
    String InvoiceNumber;//":"",
    String OrgAccountBalance;//":"",
    String ThirdPartyTransID;//":"",
    String MSISDN;//":"254708374149",
    String FirstName;//":"John",
    String MiddleName;//":"Doe",
    String LastName;//":""

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getTransID() {
        return TransID;
    }

    public void setTransID(String transID) {
        TransID = transID;
    }

    public String getTransTime() {
        return TransTime;
    }

    public void setTransTime(String transTime) {
        TransTime = transTime;
    }

    public String getTransAmount() {
        return TransAmount;
    }

    public void setTransAmount(String transAmount) {
        TransAmount = transAmount;
    }

    public String getBusinessShortCode() {
        return BusinessShortCode;
    }

    public void setBusinessShortCode(String businessShortCode) {
        BusinessShortCode = businessShortCode;
    }

    public String getBillRefNumber() {
        return BillRefNumber;
    }

    public void setBillRefNumber(String billRefNumber) {
        BillRefNumber = billRefNumber;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public String getOrgAccountBalance() {
        return OrgAccountBalance;
    }

    public void setOrgAccountBalance(String orgAccountBalance) {
        OrgAccountBalance = orgAccountBalance;
    }

    public String getThirdPartyTransID() {
        return ThirdPartyTransID;
    }

    public void setThirdPartyTransID(String thirdPartyTransID) {
        ThirdPartyTransID = thirdPartyTransID;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
