package com.derick.service.implemetation;

import com.derick.domain.UploadPrescription;
import com.derick.domain.User;
import com.derick.dto.prescription.NewPrescriptionDto;
import com.derick.dto.prescription.PrescriptionResponse;
import com.derick.mapper.prescription.UploadPrescriptionMapper;
import com.derick.mapper.user.UserMapper;
import com.derick.repository.IUploadPrescriptionRepository;
import com.derick.repository.IUserRepository;
import com.derick.service.IUploadPrescriptionService;
import com.derick.service.IUserService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UploadPrescriptionService implements IUploadPrescriptionService {

    @Autowired
    IUploadPrescriptionService uploadPrescriptionService;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IUploadPrescriptionRepository uploadPrescriptionRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IUserService userService;

    @Autowired
    UploadPrescriptionMapper prescriptionMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public PrescriptionResponse saveUploadPrescription(NewPrescriptionDto uploadPrescription) throws Exception {
        PrescriptionResponse response=new PrescriptionResponse();
        response.setResponse("failed");
        try{
            UploadPrescription prescription=prescriptionMapper.convertToEntity(uploadPrescription);
            prescription.setDateUploaded(new Date());
            prescription.setUser(entityManager.find(User.class,uploadPrescription.getUserId()));
            //entityManager.
            //entityManager.(prescription);
            uploadPrescriptionRepository.save(prescription);
            response.setPresciption(prescriptionMapper.convertToDto(prescription));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    @Transactional
    public PrescriptionResponse getAllUploadPrescription(int CustomerId) throws Exception {
        PrescriptionResponse response=new PrescriptionResponse();
        response.setResponse("failed");
        try{
            User user=userRepository.findById(CustomerId).get();
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<UploadPrescription> query=builder.createQuery(UploadPrescription.class);
            Root<UploadPrescription> root=query.from(UploadPrescription.class);
            query.select(root).where(builder.equal(root.get("user"),user));
            Query q=entityManager.createQuery(query);
            List<UploadPrescription> prescriptions=new ArrayList<>();
            prescriptions=q.getResultList();

            response.setResponse("success");
            response.setPresciptions(prescriptionMapper.convertToDto(prescriptions));
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public PrescriptionResponse getUploadPrescription(int UploadPrescriptionId) throws Exception {
        PrescriptionResponse response=new PrescriptionResponse();
        response.setResponse("failed");
        try{
            UploadPrescription prescription= uploadPrescriptionRepository.findById(UploadPrescriptionId).get();

            response.setPresciption(prescriptionMapper.convertToDto(prescription));
            response.setResponse("success");

            return response;
            //return uploadPrescriptionService.getUploadPrescription(UploadPrescriptionId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public PrescriptionResponse deleteUploadPrescription(int UploadPrescriptionId) throws Exception {
        PrescriptionResponse response=new PrescriptionResponse();
        try{
            /*response=getUploadPrescription(UploadPrescriptionId);
            if(response.getResponse().equalsIgnoreCase("success")){
                //UploadPrescription uploadPrescription=prescriptionMapper.convertToEntity(response.getPresciption());
                //UploadPrescription uploadPrescription=uploadPrescriptionRepository.deleteById(UploadPrescriptionId);
                uploadPrescriptionRepository.deleteById(UploadPrescriptionId);
                //uploadPrescriptionRepository.delete(uploadPrescription);

                response=new PrescriptionResponse();
                response.setResponse("success");

                return response;
            }*/
            uploadPrescriptionRepository.deleteById(UploadPrescriptionId);
            response=new PrescriptionResponse();
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        response=new PrescriptionResponse();
        response.setResponse("failed");
        return response;
    }
}
