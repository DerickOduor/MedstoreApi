package com.derick.external.firebasemessaging;

import com.derick.dto.chat.SendNotification;
import com.derick.service.ISystemParameterService;
import com.derick.utils.LogFile;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Fcm {

    @Autowired
    LogFile logFile;

    @Autowired
    ISystemParameterService systemParameterService;
    Gson gson=new Gson();

    public void PushNotification(SendNotification sendNotification){
        String FcmUrl="";
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            String json="{\n" +
                    " \"to\" : \"eNZ8-1O8cZ8:APA91bHhWu_xq4oLx8qqxndArVJPF-52W0nJLTTyVgj2QNbMG7v7x56QKiTOtIZlgm5w4UDovu7vpo4F9c2SnVujpoF0xn5SYRP30-0aH43AP1VrlIW_O6vWiy75gJArY3P72ZeToohl\",\n" +
                    " \"collapse_key\" : \"type_a\",\n" +
                    " \"notification\" : {\n" +
                    "     \"body\" : \"Body of Your Notification\",\n" +
                    "     \"title\": \"Title of Your Notification\"\n" +
                    " },\n" +
                    " \"data\" : {\n" +
                    "     \"body\" : \"Body of Your Notification in Data\",\n" +
                    "     \"title\": \"Title of Your Notification in Title\",\n" +
                    "     \"key_1\" : \"Value for key_1\",\n" +
                    "     \"key_2\" : \"Value for key_2\"\n" +
                    " }\n" +
                    "}";
            json=gson.toJson(sendNotification);
            String AuthorizationKey=systemParameterService.getSystemParameter("FcmAuthorizationKey").getValue();
            FcmUrl=systemParameterService.getSystemParameter("FcmUrl").getValue();
            RequestBody body = RequestBody.create(mediaType,json);
            Request request = new Request.Builder()
                    //.url("https://fcm.googleapis.com/fcm/send")
                    .url(FcmUrl)
                    .post(body)
                    .addHeader("Authorization", "key="+AuthorizationKey)
                    /*.addHeader("Authorization", "key=AAAAQ76J_a8:APA91bEGmrf3egLFeK6a" +
                            "refx4SkMK1hQVzlJciHVdw4AwOj3BWJYg7iN23G9pQz5w15KSDGbpxo20usH-zJMpeD3WM8lMTo0RAFkMv3rB0QC" +
                            "HyABWnu471ib9ztypsO8DOIwPXoYEh9q")*/
                    .addHeader("content-type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            String json_response=response.body().string();
            System.out.println("JSON: "+json_response+"\n"+gson.toJson(sendNotification));
        }catch (Exception e){
            logFile.error(e);
            System.out.println("FCM ERROR: "+FcmUrl+"\n");
            e.printStackTrace();
        }
    }

}
