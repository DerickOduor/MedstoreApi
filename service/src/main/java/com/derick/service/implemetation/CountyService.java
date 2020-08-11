package com.derick.service.implemetation;

import com.derick.domain.County;
import com.derick.dto.county.CountyDto;
import com.derick.dto.county.CountyResponse;
import com.derick.mapper.county.CountyMapper;
import com.derick.repository.ICountyRepository;
import com.derick.service.ICountyService;
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
public class CountyService implements ICountyService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ICountyRepository countyRepository;

    @Autowired
    CountyMapper countyMapper;

    @Autowired
    LogFile logFile;

    @Override
    @Transactional
    public CountyResponse getCounties() {
        CountyResponse response=new CountyResponse();
        response.setResponse("failed");
        try{
            List<County> counties=new ArrayList<>();
            counties= (List<County>) countyRepository.findAll();

            response.setResponse("success");
            response.setCountyDtos(countyMapper.convertToDto(counties));
            return response;
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public CountyResponse getCounty(int CountyId) {
        CountyResponse response=new CountyResponse();
        response.setResponse("failed");
        try{
            County county=new County();
            county= entityManager.find(County.class,CountyId);

            response.setResponse("success");
            response.setCountyDto(countyMapper.convertToDto(county));
            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    public CountyResponse getCounty(boolean Status) {
        CountyResponse response=new CountyResponse();
        response.setResponse("failed");
        try{
            County county=new County();
            List<County> counties=new ArrayList<>();
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<County> query=builder.createQuery(County.class);
            Root<County> root=query.from(County.class);
            query.select(root).where(builder.equal(root.get("Status"),Status));
            Query q=entityManager.createQuery(query);
            counties=q.getResultList();

            response.setResponse("success");
            response.setCountyDtos(countyMapper.convertToDto(counties));
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public CountyResponse getCounty(String CountyName) {
        CountyResponse response=new CountyResponse();
        response.setResponse("failed");
        try{
            County county=new County();
            List<County> counties=new ArrayList<>();
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<County> query=builder.createQuery(County.class);
            Root<County> root=query.from(County.class);
            query.select(root).where(builder.equal(root.get("Name"),CountyName.trim()));
            Query q=entityManager.createQuery(query);
            counties=q.getResultList();
            county=counties.get(0);

            response.setResponse("success");
            response.setCountyDto(countyMapper.convertToDto(county));
            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public CountyResponse saveCounty(CountyDto countyDto) {
        CountyResponse response=new CountyResponse();
        response.setResponse("failed");
        try{
            County county=countyMapper.convertToEntity(countyDto);
            entityManager.persist(county);

            response.setCountyDto(countyMapper.convertToDto(county));
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
    public CountyResponse updateCounty(CountyDto countyDto) {
        CountyResponse response=new CountyResponse();
        response.setResponse("failed");
        try{
            County c=entityManager.find(County.class,countyDto.getId());

            County county=countyMapper.convertToEntity(countyDto);
            c=county;
            entityManager.persist(c);
            //countyRepository.save(county);

            response.setResponse("success");
            response.setCountyDto(countyMapper.convertToDto(county));

            return response;
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public CountyResponse deleteCounty(int CountyId) {
        CountyResponse response=new CountyResponse();
        response.setResponse("failed");

        try{
            countyRepository.deleteById(CountyId);
            response.setResponse("success");
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }

        return response;
    }
}
