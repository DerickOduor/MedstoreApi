package com.derick.service.implemetation;

import com.derick.domain.Medicine;
import com.derick.domain.User;
import com.derick.dto.medstore.AddMedicineDto;
import com.derick.dto.medstore.AddMedicineResponse;
import com.derick.dto.medstore.ViewMedicineDto;
import com.derick.dto.medstore.ViewMedicineResponse;
import com.derick.mapper.medstore.AddMedicineMapper;
import com.derick.mapper.medstore.ViewMedicineMapper;
import com.derick.repository.IMedicineRepository;
import com.derick.service.IMedicineService;
import javassist.NotFoundException;
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
public class MedicineService implements IMedicineService {

    @Autowired
    IMedicineRepository medicineRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ViewMedicineMapper viewMedicineMapper;

    @Autowired
    AddMedicineMapper addMedicineMapper;

    @Override
    @Transactional
    public ViewMedicineResponse getMedicine(int id) throws NotFoundException {
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        try{
            Medicine medicine=entityManager.find(Medicine.class,id);
            viewMedicineResponse.setMedicine(viewMedicineMapper.convertToDto(medicine));
            viewMedicineResponse.setResponse("success");
            return viewMedicineResponse;
        }catch (Exception e){
            e.printStackTrace();
        }
        viewMedicineResponse.setResponse("failed");
        return viewMedicineResponse;
    }

    @Override
    @Transactional
    public ViewMedicineResponse getMedicine() throws NotFoundException {
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        try{
            List<Medicine> medicine= (List<Medicine>) medicineRepository.findAll();
            viewMedicineResponse.setMedicineList(viewMedicineMapper.convertToDto(medicine));
            viewMedicineResponse.setResponse("success");
            return viewMedicineResponse;
        }catch (Exception e){
            e.printStackTrace();
        }
        viewMedicineResponse.setResponse("failed");
        return viewMedicineResponse;
    }

    @Override
    public ViewMedicineResponse getPharmacyMedicine(int PharmacyId) throws NotFoundException {
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        viewMedicineResponse.setResponse("failed");
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<Medicine> query=builder.createQuery(Medicine.class);
            Root<Medicine> root=query.from(Medicine.class);
            query.select(root).where(builder.equal(root.get("pharmacy.id"),PharmacyId));
            Query q=entityManager.createQuery(query);

            List<Medicine> medicine= new ArrayList<>();
            medicine=q.getResultList();
            viewMedicineResponse.setMedicineList(viewMedicineMapper.convertToDto(medicine));
            viewMedicineResponse.setResponse("success");
            return viewMedicineResponse;
        }catch (Exception e){
            e.printStackTrace();
        }
        return viewMedicineResponse;
    }

    @Override
    @Transactional
    public ViewMedicineResponse getMedicine(String key) throws NotFoundException {
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<Medicine> query=builder.createQuery(Medicine.class);
            Root<Medicine> root=query.from(Medicine.class);
            query.select(root).where(builder.like(root.get("Name"),"%"+key+"%"));
            Query q=entityManager.createQuery(query);

            List<Medicine> medicine= new ArrayList<>();
            medicine=q.getResultList();
            viewMedicineResponse.setMedicineList(viewMedicineMapper.convertToDto(medicine));
            viewMedicineResponse.setResponse("success");
            return viewMedicineResponse;
        }catch (Exception e){
            e.printStackTrace();
        }
        viewMedicineResponse.setResponse("failed");
        return viewMedicineResponse;
    }

    @Override
    @Transactional
    public ViewMedicineResponse addMedicine(AddMedicineDto medicineDto) throws NotFoundException {
        ViewMedicineResponse addMedicineResponse=new ViewMedicineResponse();
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<Medicine> query=builder.createQuery(Medicine.class);
            Root<Medicine> root=query.from(Medicine.class);
            query.select(root).where(builder.equal(root.get("Name"),medicineDto.getName().trim()));
            Query q=entityManager.createQuery(query);

            List<Medicine> medicine=q.getResultList();

            if(medicine.size()==0){
                Medicine medicine1=addMedicineMapper.convertToEntity(medicineDto);

                try{
                    medicine1.setPriceAfterDiscount(((100.0-medicine1.getDiscount())*medicine1.getPrice())/100);
                }catch (Exception e){
                    e.printStackTrace();
                }

                medicineRepository.save(medicine1);

                addMedicineResponse.setMedicine(viewMedicineMapper.convertToDto(medicine1));
                addMedicineResponse.setResponse("success");
                return addMedicineResponse;
            }else{
                addMedicineResponse.setResponse("Medicine already exists!");
                return addMedicineResponse;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        addMedicineResponse.setResponse("failed");
        return addMedicineResponse;
    }

    @Override
    @Transactional
    public AddMedicineResponse updateMedicine(ViewMedicineDto medicineDto) throws NotFoundException {
        Medicine medicine=null;
        AddMedicineResponse medicineResponse=new AddMedicineResponse();
        try{
            medicine=viewMedicineMapper.convertToEntity(getMedicine(medicineDto.getId()).getMedicine());

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(medicine!=null){
                medicine.setName(medicineDto.getName());
                medicine.setPrice(medicineDto.getPrice());
                medicine.setMedicineImage(medicineDto.getMedicineImage());

                entityManager.persist(medicine);
                medicineResponse.setResponse("success");
                medicineResponse.setMedicineDto(viewMedicineMapper.convertToDto(medicine));

                return medicineResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        medicineResponse.setResponse("failed");

        return medicineResponse;
    }
}
