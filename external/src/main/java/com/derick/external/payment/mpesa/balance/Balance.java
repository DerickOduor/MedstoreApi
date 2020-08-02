package com.derick.external.payment.mpesa.balance;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Balance {
    final static Gson gson=new Gson();
    @SuppressWarnings("deprecation")
    public static void checkBalance(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"Initiator\":\" \"," +
                "\"SecurityCredential\":\" \",\"CommandID\":\"AccountBalance\",\"PartyA\":\" \"," +
                "\"IdentifierType\":\"4\",\"Remarks\":\" \"," +
                "\"QueueTimeOutURL\":\"https://ip_address:port/timeout_url\"," +
                "\"ResultURL\":\"https://ip_address:port/result_url\"}");
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/accountbalance/v1/query")
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
