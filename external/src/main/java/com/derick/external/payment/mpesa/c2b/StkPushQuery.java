package com.derick.external.payment.mpesa.c2b;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class StkPushQuery {
    final static Gson gson=new Gson();
    public static void AccessToken(){
        try{
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"BusinessShortCode\":\" \" ," +
                    "\"Password\":\" \",\"Timestamp\":\" \"," +
                    "\"CheckoutRequestID\":\" \"}");
            Request request = new Request.Builder()
                    .url("https://sandbox.safaricom.co.ke/mpesa/stkpushquery/v1/query")
                    .post(body)
                    .addHeader("authorization", "Bearer ACCESS_TOKEN")
                    .addHeader("content-type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            try{
                System.out.println("Response: "+gson.toJson(response));
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
