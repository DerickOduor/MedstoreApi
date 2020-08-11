package com.derick.service.implemetation;

import com.derick.domain.PaymentOption;
import com.derick.dto.payment.option.PaymentOptionDto;
import com.derick.dto.payment.option.PaymentOptionResponse;
import com.derick.mapper.payment.option.PaymentOptionMapper;
import com.derick.service.IPaymentOptionService;
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
public class PaymentOptionService implements IPaymentOptionService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    LogFile logFile;

    @Autowired
    PaymentOptionMapper paymentOptionMapper;

    @Override
    @Transactional
    public PaymentOptionResponse getPaymentoptions() {
        PaymentOptionResponse response=new PaymentOptionResponse();
        response.setResponse("failed");
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<PaymentOption> query=builder.createQuery(PaymentOption.class);
            Root<PaymentOption> root=query.from(PaymentOption.class);
            query.select(root);
            Query q=entityManager.createQuery(query);
            List<PaymentOption> paymentOptions=new ArrayList<>();
            paymentOptions=q.getResultList();

            response.setPaymentOptionDtos(paymentOptionMapper.convertToDto(paymentOptions));
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
    public PaymentOptionResponse getPaymentoptions(boolean Status) {
        PaymentOptionResponse response=new PaymentOptionResponse();
        response.setResponse("failed");
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<PaymentOption> query=builder.createQuery(PaymentOption.class);
            Root<PaymentOption> root=query.from(PaymentOption.class);
            query.select(root).where(builder.equal(root.get("Active"),Status));
            Query q=entityManager.createQuery(query);
            List<PaymentOption> paymentOptions=new ArrayList<>();
            paymentOptions=q.getResultList();

            response.setPaymentOptionDtos(paymentOptionMapper.convertToDto(paymentOptions));
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
    public PaymentOptionResponse getPaymentoption(int id) {
        PaymentOptionResponse response=new PaymentOptionResponse();
        response.setResponse("failed");
        try{
            PaymentOption paymentOption=entityManager.find(PaymentOption.class,id);
            response.setPaymentOptionDto(paymentOptionMapper.convertToDto(paymentOption));
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
    public PaymentOptionResponse deletePaymentoption(int id) {
        PaymentOptionResponse response=new PaymentOptionResponse();
        response.setResponse("failed");
        try{
            PaymentOption paymentOption=entityManager.find(PaymentOption.class,id);
            entityManager.remove(paymentOption);

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
    public PaymentOptionResponse addPaymentoption(PaymentOptionDto paymentOptionDto) {
        PaymentOptionResponse response=new PaymentOptionResponse();
        response.setResponse("failed");
        try{
            PaymentOption paymentOption=paymentOptionMapper.convertToEntity(paymentOptionDto);
            entityManager.persist(paymentOption);

            response.setPaymentOptionDto(paymentOptionDto);
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
    public PaymentOptionResponse updatePaymentoption(PaymentOptionDto paymentOptionDto) {
        PaymentOptionResponse response=new PaymentOptionResponse();
        response.setResponse("failed");
        try{
            PaymentOption paymentOption=entityManager.find(PaymentOption.class,paymentOptionDto.getId());
            paymentOption=paymentOptionMapper.convertToEntity(paymentOptionDto);
            entityManager.persist(paymentOption);

            response.setPaymentOptionDto(paymentOptionMapper.convertToDto(paymentOption));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        return response;
    }
}
