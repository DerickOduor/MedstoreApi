package com.derick.service.implemetation;

import com.derick.domain.MpesaParameter;
import com.derick.domain.MpesaUrl;
import com.derick.dto.payment.mpesa.MpesaResponse;
import com.derick.dto.payment.mpesa.MpesaUrlDto;
import com.derick.mapper.payment.mpesa.MpesaParameterMapper;
import com.derick.mapper.payment.mpesa.MpesaUrlMapper;
import com.derick.service.IMpesaUrlService;
import com.derick.utils.LogFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class MpesaUrlService implements IMpesaUrlService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    MpesaUrlMapper mpesaUrlMapper;

    @Autowired
    LogFile logFile;

    @Override
    @Transactional
    public MpesaResponse getUrl(String Name) {
        MpesaResponse response=new MpesaResponse();
        response.setResponse("failed");
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<MpesaUrl> query=builder.createQuery(MpesaUrl.class);
            Root<MpesaUrl> root=query.from(MpesaUrl.class);
            query.select(root).where(builder.equal(root.get("Name"),Name));
            Query q=entityManager.createQuery(query);

            List<MpesaUrl> mpesaUrls= new ArrayList<>();
            mpesaUrls=q.getResultList();
            response.setMpesaUrlDto(mpesaUrlMapper.convertToDto(mpesaUrls.get(0)));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }

        return response;
    }

    @Override
    @Transactional
    public MpesaResponse getUrl(int Id) {
        MpesaResponse response=new MpesaResponse();
        response.setResponse("failed");
        try{
            MpesaUrl mpesaUrl=entityManager.find(MpesaUrl.class,Id);

            response.setMpesaUrlDto(mpesaUrlMapper.convertToDto(mpesaUrl));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public MpesaResponse addUrl(MpesaUrlDto mpesaUrlDto) {
        MpesaResponse response=new MpesaResponse();
        response.setResponse("failed");
        try{
            List<MpesaUrl> mpesaUrls=new ArrayList<>();
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<MpesaUrl> query=builder.createQuery(MpesaUrl.class);
            Root<MpesaUrl> root=query.from(MpesaUrl.class);
            query.select(root);
            Query q=entityManager.createQuery(query);
            mpesaUrls=q.getResultList();

            if(mpesaUrls.size()==0){
                MpesaUrl url=new MpesaUrl();
                url.setName("PROCESSREQUEST");
                url.setLive("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest");
                url.setSandbox("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest");
                entityManager.persist(url);
                MpesaUrl url2=new MpesaUrl();
                url2.setName("TOKEN");
                url2.setLive("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials");
                url2.setSandbox("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials");
                entityManager.persist(url2);
                MpesaUrl url3=new MpesaUrl();
                url3.setName("STK PUSH CALLBACK URL");
                url3.setLive("https://e77de3aa4083.ngrok.io/blocker_war/api/chezanani/return");
                url3.setSandbox("https://e77de3aa4083.ngrok.io/blocker_war/api/chezanani/return");
                entityManager.persist(url3);
            }
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        try{
            //MpesaUrl mpesaUrl=mpesaUrlMapper.convertToEntity(mpesaUrlDto);
            //entityManager.persist(mpesaUrl);

            //response.setMpesaUrlDto(mpesaUrlMapper.convertToDto(mpesaUrl));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public MpesaResponse updateUrl(MpesaUrlDto mpesaUrlDto) {
        MpesaResponse response=new MpesaResponse();
        response.setResponse("failed");
        return response;
    }
}
