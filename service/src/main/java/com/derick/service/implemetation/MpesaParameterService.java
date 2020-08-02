package com.derick.service.implemetation;

import com.derick.domain.MpesaParameter;
import com.derick.dto.payment.mpesa.MpesaParameterDto;
import com.derick.dto.payment.mpesa.MpesaResponse;
import com.derick.mapper.payment.mpesa.MpesaParameterMapper;
import com.derick.service.IMpesaParameterService;
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
public class MpesaParameterService implements IMpesaParameterService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    MpesaParameterMapper mpesaParameterMapper;

    @Override
    @Transactional
    public MpesaResponse getParameter(String Name) {
        MpesaResponse response=new MpesaResponse();
        response.setResponse("failed");
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<MpesaParameter> query=builder.createQuery(MpesaParameter.class);
            Root<MpesaParameter> root=query.from(MpesaParameter.class);
            query.select(root).where(builder.equal(root.get("Name"),Name));
            Query q=entityManager.createQuery(query);

            List<MpesaParameter> mpesaParameters= new ArrayList<>();
            mpesaParameters=q.getResultList();
            System.out.println(Name+" "+mpesaParameters.size());
            response.setMpesaParameterDto(mpesaParameterMapper.convertToDto(mpesaParameters.get(0)));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public MpesaResponse getParameter(int Id) {
        MpesaResponse response=new MpesaResponse();
        response.setResponse("failed");
        try{
            MpesaParameter parameter=entityManager.find(MpesaParameter.class,Id);
            response.setMpesaParameterDto(mpesaParameterMapper.convertToDto(parameter));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public MpesaResponse addParameter(MpesaParameterDto mpesaParameterDto) {
        MpesaResponse response=new MpesaResponse();
        response.setResponse("failed");
        try{
            List<MpesaParameter> mpesaParameterList=new ArrayList<>();
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<MpesaParameter> query=builder.createQuery(MpesaParameter.class);
            Root<MpesaParameter> root=query.from(MpesaParameter.class);
            query.select(root);
            Query q=entityManager.createQuery(query);
            mpesaParameterList=q.getResultList();
            if(mpesaParameterList.size()==0){
                MpesaParameter parameter=new MpesaParameter();
                parameter.setName("LIPA NA M-PESA SHORT CODE");
                parameter.setValue("174379");
                entityManager.persist(parameter);
                MpesaParameter parameter2=new MpesaParameter();
                parameter2.setName("LIPA NA M-PESA C2B TRANSACTION TYPE");
                parameter2.setValue("CustomerPayBillOnline");
                entityManager.persist(parameter2);
                MpesaParameter parameter3=new MpesaParameter();
                parameter3.setName("LIPA NA M-PESA CALLBACK URL");
                parameter3.setValue("https://e77de3aa4083.ngrok.io/blocker_war/api/chezanani/return");
                entityManager.persist(parameter3);
                MpesaParameter parameter4=new MpesaParameter();
                parameter4.setName("APP KEY");
                parameter4.setValue("MGDuFIAq3AOR5LysWBeDCgcHqxJidzDp");
                entityManager.persist(parameter4);
                MpesaParameter parameter5=new MpesaParameter();
                parameter5.setName("APP SECRET");
                parameter5.setValue("ITkCcGSMgMozjxqq");
                entityManager.persist(parameter5);
                MpesaParameter parameter6=new MpesaParameter();
                parameter6.setName("LipaNaMpesaOnlinePasskey");
                parameter6.setValue("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919");
                entityManager.persist(parameter6);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            //MpesaParameter mpesaParameter=mpesaParameterMapper.convertToEntity(mpesaParameterDto);
            //entityManager.persist(mpesaParameterDto);

            //response.setMpesaParameterDto(mpesaParameterMapper.convertToDto(mpesaParameter));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public MpesaResponse updateParameter(MpesaParameterDto mpesaParameterDto) {
        MpesaResponse response=new MpesaResponse();
        response.setResponse("failed");
        return response;
    }
}
