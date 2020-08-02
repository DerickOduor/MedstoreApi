package com.derick.external.payment;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransactionStatus {
    final static Gson gson=new Gson();
    public static void transactionStatus(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"Initiator\": \" \"," +
                "\"SecurityCredential\": \" \",\"CommandID\":\"TransactionStatusQuery\"," +
                "\"TransactionID\": \" \",\"PartyA\": \" \",\"IdentifierType\":\"1\"," +
                "\"ResultURL\":\"https://ip_address:port/result_url\"," +
                "\"QueueTimeOutURL\":\"https://ip_address:port/timeout_url\"," +
                "\"Remarks\": \" \",\"Occasion\": \" \"}");
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/transactionstatus/v1/query")
                .post(body)
                .addHeader("authorization", "Bearer ACCESS_TOKEN")
                .addHeader("content-type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            try{
                System.out.println("Response: "+gson.toJson(response));
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
