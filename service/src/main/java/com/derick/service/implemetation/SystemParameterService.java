package com.derick.service.implemetation;

import com.derick.domain.SystemParameter;
import com.derick.service.ISystemParameterService;
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
public class SystemParameterService implements ISystemParameterService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    LogFile logFile;

    @Override
    @Transactional
    public SystemParameter getSystemParameter(String Name) throws Exception {
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<SystemParameter> query=builder.createQuery(SystemParameter.class);
            Root<SystemParameter> root=query.from(SystemParameter.class);
            query.select(root).where(builder.equal(root.get("Name"),Name));
            Query q=entityManager.createQuery(query);
            List<SystemParameter> systemParameters=new ArrayList<>();
            systemParameters=q.getResultList();

            return systemParameters.get(0);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return null;
    }

    @Override
    @Transactional
    public SystemParameter getSystemParameter(int Id) throws Exception {
        try{
            return entityManager.find(SystemParameter.class,Id);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return null;
    }

    @Override
    @Transactional
    public List<SystemParameter> getSystemParameters() throws Exception {
        return null;
    }
}
