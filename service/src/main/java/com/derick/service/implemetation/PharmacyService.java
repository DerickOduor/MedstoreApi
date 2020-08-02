package com.derick.service.implemetation;

import com.derick.domain.County;
import com.derick.domain.Pharmacy;
import com.derick.dto.pharmacy.LocationDto;
import com.derick.dto.pharmacy.PharmacyDto;
import com.derick.dto.pharmacy.PharmacyResponse;
import com.derick.mapper.pharmacy.PharmacyMapper;
import com.derick.repository.IPharmacyRepository;
import com.derick.service.IPharmacyService;
import com.derick.utils.GeoLocation;
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
public class PharmacyService implements IPharmacyService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IPharmacyRepository pharmacyRepository;

    @Autowired
    PharmacyMapper pharmacyMapper;

    @Autowired
    GeoLocation geoLocation;

    @Override
    @Transactional
    public PharmacyResponse getPharmacies() {
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("success");
        try{
            List<Pharmacy> pharmacies= (List<Pharmacy>) pharmacyRepository.findAll();
            response.setResponse("success");
            response.setPharmacyDtos(pharmacyMapper.convertToDto(pharmacies));

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public PharmacyResponse getPharmacy(int PharmacyId) {
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("success");
        try{
            Pharmacy pharmacy= entityManager.find(Pharmacy.class,PharmacyId);
            response.setResponse("success");
            response.setPharmacyDto(pharmacyMapper.convertToDto(pharmacy));

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public PharmacyResponse getPharmacy(String PharmacyName) {
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("success");
        try{
            Pharmacy pharmacy=new Pharmacy();
            List<Pharmacy> pharmacies=new ArrayList<>();
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<Pharmacy> query=builder.createQuery(Pharmacy.class);
            Root<Pharmacy> root=query.from(Pharmacy.class);
            query.select(root).where(builder.equal(root.get("Name"),PharmacyName.trim()));
            Query q=entityManager.createQuery(query);

            pharmacies=q.getResultList();
            pharmacy=pharmacies.get(0);
            response.setResponse("success");
            response.setPharmacyDto(pharmacyMapper.convertToDto(pharmacy));
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public PharmacyResponse savePharmacy(PharmacyDto pharmacyDto) {
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("success");
        try{
            Pharmacy pharmacy=pharmacyMapper.convertToEntity(pharmacyDto);
            entityManager.persist(pharmacy);

            response.setResponse("success");
            response.setPharmacyDto(pharmacyMapper.convertToDto(pharmacy));

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public PharmacyResponse updatePharmacy(PharmacyDto pharmacyDto) {
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("success");
        try{
            Pharmacy c=entityManager.find(Pharmacy.class,pharmacyDto.getId());

            Pharmacy pharmacy=pharmacyMapper.convertToEntity(pharmacyDto);
            c=pharmacy;
            entityManager.persist(c);
            //countyRepository.save(county);
            response.setResponse("success");
            response.setPharmacyDto(pharmacyMapper.convertToDto(pharmacy));

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public PharmacyResponse deletePharmacy(int PharmacyId) {
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("success");
        try{
            pharmacyRepository.deleteById(PharmacyId);

            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public PharmacyResponse getPharmaciesByLocation(LocationDto locationDto) {
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("success");
        try{
            List<Pharmacy> pharmacies= (List<Pharmacy>) pharmacyRepository.findAll();

            List<Pharmacy> nearestPharmacies=new ArrayList<>();
            try{
                for(Pharmacy pharmacy:pharmacies){
                    double latitude=0.0;
                    double longitude=0.0;

                    if(geoLocation.distance(locationDto.getLatitude(),
                            locationDto.getLongitude(),
                            Long.parseLong(pharmacy.getLatitude()),
                            Long.parseLong(pharmacy.getLongitude()),
                            "K")<2)
                    {
                        nearestPharmacies.add(pharmacy);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            response.setResponse("success");
            response.setPharmacyDtos(pharmacyMapper.convertToDto(nearestPharmacies));

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}