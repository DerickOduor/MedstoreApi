package com.derick.external.payment.mpesa.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.derick.service.IMpesaParameterService;
import com.derick.service.IMpesaUrlService;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityUtil {

    @Autowired
    IMpesaParameterService mpesaParameterService;

    @Autowired
    IMpesaUrlService mpesaUrlService;

    final static Gson gson=new Gson();

    public static String getTimestamp(){
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        return timeStamp;
    }

    //Function to encrypt the initiator credentials
    /*public static String encryptInitiatorPassword(String securityCertificate, String password) {
        String encryptedPassword = "YOUR_INITIATOR_PASSWORD";
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] input = password.getBytes();

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            FileInputStream fin = new FileInputStream(new File(securityCertificate));
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) cf.generateCertificate(fin);
            PublicKey pk = certificate.getPublicKey();
            cipher.init(Cipher.ENCRYPT_MODE, pk);

            byte[] cipherText = cipher.doFinal(input);

            // Convert the resulting encrypted byte array into a string using base64 encoding
            encryptedPassword = Base64.getEncoder().encodeToString(cipherText);
//Base64.encode(cipherText);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger("PasswordUtil").log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger("PasswordUtil").log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger("PasswordUtil").log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger("PasswordUtil").log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger("PasswordUtil").log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger("PasswordUtil").log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger("PasswordUtil").log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger("PasswordUtil").log(Level.SEVERE, null, ex);
        }

        return encryptedPassword;
    }*/

    public String AccessToken(){
        try{
            String app_key =mpesaParameterService.getParameter("APP KEY").getMpesaParameterDto().getValue(); //"MGDuFIAq3AOR5LysWBeDCgcHqxJidzDp";
            String app_secret = mpesaParameterService.getParameter("APP SECRET").getMpesaParameterDto().getValue();//"ITkCcGSMgMozjxqq";
            String appKeySecret = app_key + ":" + app_secret;
            byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
            String auth = Base64.getEncoder().encodeToString(bytes);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(mpesaUrlService.getUrl("TOKEN".trim()).getMpesaUrlDto().getSandbox()/*"https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials"*/)
                    .get()
                    .addHeader("authorization", "Basic " + auth)
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();
            try{
                //System.out.println("Response: "+gson.toJson(response.body()));
                Token token=gson.fromJson(response.body().string(),Token.class);
                //System.out.println("ResponseToken: "+gson.toJson(token));

                return token.access_token;
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
