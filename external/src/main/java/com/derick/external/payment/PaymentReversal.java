package com.derick.external.payment;

import com.derick.utils.LogFile;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymentReversal {
    final static Gson gson=new Gson();
    @Autowired
    LogFile logFile;
    public void reversePayment(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"Initiator\":\" \"," +
                "\"SecurityCredential\":\" \",\"CommandID\":\"TransactionReversal\"," +
                "\"TransactionID\":\" \",\"Amount\":\" \",\"ReceiverParty\":\" \"," +
                "\"RecieverIdentifierType\":\"4\",\"ResultURL\":\"https://ip_address:port/result_url\"," +
                "\"QueueTimeOutURL\":\"https://ip_address:port/timeout_url\",\"Remarks\":\" \"," +
                "\"Occasion\":\" \"}");
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/reversal/v1/request")
                .post(body)
                .addHeader("authorization", "Bearer ACCESS_TOKEN")
                .addHeader("content-type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            try{
                System.out.println("Response: "+gson.toJson(response));
            }catch (Exception e){
                logFile.error(e);
                e.printStackTrace();
            }
        } catch (IOException e) {
            logFile.error(e);
            e.printStackTrace();
        }
    }
}
