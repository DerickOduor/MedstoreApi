package com.derick.service.implemetation;

import com.derick.domain.StkPushRequest;
import com.derick.dto.payment.mpesa.stkpush.Item;
import com.derick.dto.payment.mpesa.stkpush.MpesaCallBackResponse;
import com.derick.service.IStkPushService;
import com.derick.utils.LogFile;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class StkPushService implements IStkPushService {

    @PersistenceContext
    EntityManager entityManager;

    Gson gson=new Gson();

    @Autowired
    LogFile logFile;

    @Override
    @Transactional
    public void addStkPushRequest(StkPushRequest stkPushRequest) {
        try{
            entityManager.persist(stkPushRequest);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void addStkPushResponse(MpesaCallBackResponse mpesaCallBackResponse) {
        try{
            com.derick.domain.MpesaCallBackResponse callBackResponse=new com.derick.domain.MpesaCallBackResponse();
            callBackResponse.setCheckoutRequestID(mpesaCallBackResponse.getBody().getStkCallback().getCheckoutRequestID());
            callBackResponse.setMerchantRequestID(mpesaCallBackResponse.getBody().getStkCallback().getMerchantRequestID());
            callBackResponse.setResultCode(mpesaCallBackResponse.getBody().getStkCallback().getResultCode());
            callBackResponse.setResultDesc(mpesaCallBackResponse.getBody().getStkCallback().getResultDesc());

            if(callBackResponse.getResultCode()==0){
                System.out.println("STK Accepted");
                for(Item item:mpesaCallBackResponse.getBody().getStkCallback().getCallbackMetadata().getItem()){
                    if(item.getName().equalsIgnoreCase("Amount")){
                        System.out.println("Amount: "+item.getValue());
                        callBackResponse.setAmount(Double.parseDouble(item.getValue()));
                    }
                    if(item.getName().equalsIgnoreCase("MpesaReceiptNumber")){
                        System.out.println("MpesaReceiptNumber: "+item.getValue());
                        callBackResponse.setMpesaReceiptNumber((item.getValue()));
                    }
                    if(item.getName().equalsIgnoreCase("TransactionDate")){
                        System.out.println("TransactionDate: "+item.getValue());
                        callBackResponse.setTransactionDate((item.getValue()));
                    }
                    if(item.getName().equalsIgnoreCase("PhoneNumber")){
                        System.out.println("PhoneNumber: "+item.getValue());
                        callBackResponse.setPhoneNumber((item.getValue()));
                    }
                    System.out.println("Item: "+item.getName()+" Value: "+item.getValue());
                }
            }else{
                System.out.println("STK Cancelled");
            }
            System.out.println("TO DB: "+gson.toJson(callBackResponse));
            entityManager.persist(callBackResponse);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
    }
}
