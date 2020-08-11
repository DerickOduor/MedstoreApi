package com.derick.external.payment.mpesa.c2b;

import com.derick.domain.StkPushRequest;
import com.derick.dto.order.CustomerOrderDto;
import com.derick.external.payment.mpesa.security.SecurityUtil;
import com.derick.service.IMpesaParameterService;
import com.derick.service.IMpesaUrlService;
import com.derick.utils.LogFile;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Component
public class StkPush {

   // private static IMpesaUrlService mpesaUrlService;
    @Autowired
    IMpesaParameterService mpesaParameterService;

    @Autowired
    IMpesaUrlService mpesaUrlService;

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    LogFile logFile;

    final static Gson gson=new Gson();
    @SuppressWarnings("deprecation")
    public String processPayment(){
        try{
            OkHttpClient client = new OkHttpClient();

            Date now=new Date(System.currentTimeMillis());
            System.out.println("CurrentTime: "+new SimpleDateFormat("yyyyMMddHHmmss").format(now));

            System.out.println("SysColock");

            String timest=SecurityUtil.getTimestamp();//new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            String SecurityCredential="Apitest439#";
            String LipaNaMpesaOnlinePasskey=mpesaParameterService.getParameter("LipaNaMpesaOnlinePasskey").getMpesaParameterDto().getValue()/*"bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"*/;
            //base64.encode(Shortcode+Passkey+Timestamp)
            //byte[] bytes = ("174379"+LipaNaMpesaOnlinePasskey+timest.trim()).getBytes("ISO-8859-1");
            byte[] bytes = ("174379"+LipaNaMpesaOnlinePasskey+timest.trim()).getBytes();
            String Password= Base64.getEncoder().encodeToString(bytes);
            Stk stk=new Stk();
            MediaType mediaType = MediaType.parse("application/json");

            stk.setBusinessShortCode(mpesaParameterService.getParameter("LIPA NA M-PESA SHORT CODE").getMpesaParameterDto().getValue());
            stk.setPassword(Password.trim());
            stk.setTimestamp(timest.trim());
            stk.setTransactionType(mpesaParameterService.getParameter("LIPA NA M-PESA C2B TRANSACTION TYPE").getMpesaParameterDto().getValue()/*"CustomerPayBillOnline"*/);
            stk.setAmount("1");
            stk.setPartyA("254715812661");
            stk.setPartyB(mpesaParameterService.getParameter("LIPA NA M-PESA SHORT CODE").getMpesaParameterDto().getValue());
            stk.setPhoneNumber("254715812661");
            //stk.setCallBackURL("https://279b38e6e95c.ngrok.io/blocker_war/api/chezanani/return");
            //stk.setCallBackURL("https://279b38e6e95c.ngrok.io/api/chezanani/return");
            stk.setCallBackURL(mpesaUrlService.getUrl("STK PUSH CALLBACK URL".trim()).getMpesaUrlDto().getSandbox()/*"https://e77de3aa4083.ngrok.io/blocker_war/api/chezanani/return"*/);
            stk.setAccountReference("sasas");
            stk.setTransactionDesc("asdasd");
            String json=gson.toJson(stk);
            logFile.events("M-Pesa STKPUSH RESPONSE: \n"+json);
            RequestBody body = RequestBody.create(mediaType,json);
            Request request = new Request.Builder()
                    .url("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest")
                    .post(body)
                    .addHeader("authorization", "Bearer "+ securityUtil.AccessToken())
                    .addHeader("content-type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            String json_response=response.body().string();
            logFile.events("STKPUSH: "+json_response);
            try{
               // System.out.println("Timest: "+timest+" Password: "+Password.trim());
                System.out.println("ResponseTUGYU: "+gson.toJson(json_response));
                StkPushRequest pushRequest=new StkPushRequest();
                try{
                    pushRequest=gson.fromJson(json_response,StkPushRequest.class);

                    return pushRequest.getCustomerMessage();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "failed";
    }

    @Async("threadPoolTaskExecutor")
    public String processPayment(CustomerOrderDto customerOrderDto,String Phone){
        try{
            OkHttpClient client = new OkHttpClient();

            Date now=new Date(System.currentTimeMillis());
            System.out.println("CurrentTime: "+new SimpleDateFormat("yyyyMMddHHmmss").format(now));

            System.out.println("SysColock");

            String timest=SecurityUtil.getTimestamp();//new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            String SecurityCredential="Apitest439#";
            String LipaNaMpesaOnlinePasskey=mpesaParameterService.getParameter("LipaNaMpesaOnlinePasskey").getMpesaParameterDto().getValue()/*"bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"*/;
            //base64.encode(Shortcode+Passkey+Timestamp)
            //byte[] bytes = ("174379"+LipaNaMpesaOnlinePasskey+timest.trim()).getBytes("ISO-8859-1");
            byte[] bytes = ("174379"+LipaNaMpesaOnlinePasskey+timest.trim()).getBytes();
            String Password= Base64.getEncoder().encodeToString(bytes);
            Stk stk=new Stk();
            MediaType mediaType = MediaType.parse("application/json");

            stk.setBusinessShortCode(mpesaParameterService.getParameter("LIPA NA M-PESA SHORT CODE").getMpesaParameterDto().getValue());
            stk.setPassword(Password.trim());
            stk.setTimestamp(timest.trim());
            stk.setTransactionType(mpesaParameterService.getParameter("LIPA NA M-PESA C2B TRANSACTION TYPE").getMpesaParameterDto().getValue()/*"CustomerPayBillOnline"*/);
            stk.setAmount("1");
            stk.setPartyA(Phone/*"254715812661"*/);
            stk.setPartyB(mpesaParameterService.getParameter("LIPA NA M-PESA SHORT CODE").getMpesaParameterDto().getValue());
            stk.setPhoneNumber(Phone/*"254715812661"*/);
            //stk.setCallBackURL("https://279b38e6e95c.ngrok.io/blocker_war/api/chezanani/return");
            //stk.setCallBackURL("https://279b38e6e95c.ngrok.io/api/chezanani/return");
            stk.setCallBackURL(mpesaUrlService.getUrl("STK PUSH CALLBACK URL".trim()).getMpesaUrlDto().getSandbox()/*"https://e77de3aa4083.ngrok.io/blocker_war/api/chezanani/return"*/);
            stk.setAccountReference(customerOrderDto.getOrderNumber());
            stk.setTransactionDesc(customerOrderDto.getOrderNumber());
            String json=gson.toJson(stk);
            logFile.events("M-Pesa STKPUSH RESPONSE: \n"+json);
            RequestBody body = RequestBody.create(mediaType,json);
            Request request = new Request.Builder()
                    .url("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest")
                    .post(body)
                    .addHeader("authorization", "Bearer "+ securityUtil.AccessToken())
                    .addHeader("content-type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            String json_response=response.body().string();
            logFile.events("M-Pesa STKPUSH RESPONSE: \n"+json_response);
            try{
               // System.out.println("Timest: "+timest+" Password: "+Password.trim());
                System.out.println("ResponseTUGYU: "+gson.toJson(json_response));
                StkPushRequest pushRequest=new StkPushRequest();
                try{
                    pushRequest=gson.fromJson(json_response,StkPushRequest.class);

                    return pushRequest.getCustomerMessage();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "failed";
    }

}
