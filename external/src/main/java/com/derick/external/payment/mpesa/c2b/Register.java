package com.derick.external.payment.mpesa.c2b;

import com.derick.external.payment.mpesa.security.SecurityUtil;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Register {
    final static Gson gson=new Gson();

    @Autowired
    SecurityUtil securityUtil;
    @SuppressWarnings("deprecation")
    public  void registerUrl(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"ShortCode\":\"600439\"," +
                "\"ResponseType\":\"Completed\"," +
                "\"ConfirmationURL\":\"https://c87af4665ecf.ngrok.io/blocker_war/api/chezanani/se\"," +
                "\"ValidationURL\":\"https://c87af4665ecf.ngrok.io/blocker_war/api/chezanani/ma\"}");
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/registerurl")
                .post(body)
                .addHeader("authorization", "Bearer "+ securityUtil.AccessToken())
                .addHeader("content-type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            try{
                System.out.println("ResponseAGH: "+gson.toJson(response.body().string()));
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
