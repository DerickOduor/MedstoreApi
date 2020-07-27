package com.derick.service;

import com.derick.domain.Medicine;
import com.derick.domain.PrescriptionQuotation;
import com.derick.domain.UploadPrescription;
import com.derick.domain.User;
import com.derick.dto.quotation.AddQuotationDto;
import com.derick.dto.quotation.QuotationDto;
import com.derick.dto.quotation.QuotationResponse;
import com.derick.mapper.quotation.QuotationMapper;
import com.derick.repository.IMedicineRepository;
import com.derick.repository.IPrescriptionQuotationRepository;
import com.derick.repository.IUploadPrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class PrescriptionQuotationService implements IPrescriptionQuotationService{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IPrescriptionQuotationRepository quotationRepository;

    @Autowired
    IMedicineRepository medicineRepository;

    @Autowired
    IUploadPrescriptionRepository prescriptionRepository;

    @Autowired
    QuotationMapper quotationMapper;

    @Override
    @Transactional
    public QuotationResponse sendQuotation(AddQuotationDto quotationDto) throws Exception {
        QuotationResponse response=new QuotationResponse();
        response.setResponse("failed");
        UploadPrescription prescription=null;
        List<Medicine> medicineList=null;
        PrescriptionQuotation quotation=new PrescriptionQuotation();
        try{
            prescription=entityManager.find(UploadPrescription.class,quotationDto.getPrescriptionId());
            medicineList=(List<Medicine>) medicineRepository.findAllById(quotationDto.getMedicineIds());
            double totalprice=0.0;
            for(Medicine medicine:medicineList){
                totalprice=medicine.getPriceAfterDiscount();
            }

            Set<Medicine> medicineSet=new HashSet<>(medicineList);
            medicineSet.addAll(medicineList);

            quotation.setMedicines(medicineSet);
            quotation.setTotalPrice(totalprice);
            quotation.setDateSent(new Date());
            quotation.setPrescription(prescription);
            quotation.setStatus(false);
            quotation.setUser(prescription.getUser());

            entityManager.persist(quotation);

            response.setResponse("success");
            response.setQuotation(quotationMapper.convertToDto(quotation));

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public QuotationResponse updateQuotation(QuotationDto quotationDto) throws Exception {
        QuotationResponse response=new QuotationResponse();
        response.setResponse("failed");
        return response;
    }

    @Override
    @Transactional
    public QuotationResponse viewQuotation(int QuotationId) throws Exception {
        QuotationResponse response=new QuotationResponse();
        response.setResponse("failed");

        try{
            PrescriptionQuotation quotation=entityManager.find(PrescriptionQuotation.class,QuotationId);
            response.setQuotation(quotationMapper.convertToDto(quotation));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    @Transactional
    public QuotationResponse viewCustomerQuotations(int CustomerId) throws Exception {
        QuotationResponse response=new QuotationResponse();
        response.setResponse("failed");

        User user=null;
        try{
            user=entityManager.find(User.class,CustomerId);
            List<PrescriptionQuotation> quotations=new ArrayList<>();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PrescriptionQuotation> query = builder.createQuery(PrescriptionQuotation.class);
            Root<PrescriptionQuotation> root = query.from(PrescriptionQuotation.class);
            query.select(root).where(builder.equal(root.get("user"), user));
            Query q = entityManager.createQuery(query);

            quotations=q.getResultList();

            response.setQuotations(quotationMapper.convertToDto(quotations));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    @Transactional
    public QuotationResponse deleteQuotation(int QuotationId) throws Exception {
        QuotationResponse response=new QuotationResponse();
        response.setResponse("failed");
        try{
            quotationRepository.deleteById(QuotationId);

            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public QuotationResponse deleteCustomerQuotations(int CustomerId) throws Exception {
        QuotationResponse response=new QuotationResponse();
        response.setResponse("failed");

        User user=null;
        try{
            user=entityManager.find(User.class,CustomerId);
            List<PrescriptionQuotation> quotations=new ArrayList<>();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PrescriptionQuotation> query = builder.createQuery(PrescriptionQuotation.class);
            Root<PrescriptionQuotation> root = query.from(PrescriptionQuotation.class);
            query.select(root).where(builder.equal(root.get("user"), user));
            Query q = entityManager.createQuery(query);

            quotations=q.getResultList();

            quotationRepository.deleteAll(quotations);

            response.setQuotations(quotationMapper.convertToDto(quotations));
            response.setResponse("success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
