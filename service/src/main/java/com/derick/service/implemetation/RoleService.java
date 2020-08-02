package com.derick.service.implemetation;

import com.derick.domain.Role;
import com.derick.dto.RoleDtoResponse;
import com.derick.dto.userrole.RoleDto;
import com.derick.mapper.roles.RoleMapper;
import com.derick.repository.IRoleRepository;
import com.derick.service.IRoleService;
import com.google.gson.Gson;
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
public class RoleService implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    IRoleRepository roleRepository;

    @PersistenceContext
    EntityManager entityManager;

    /*@Autowired
    Gson gson;*/

    @Override
    @Transactional
    public RoleDtoResponse getRole(int id) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public Role getRole(String name) throws Exception {

        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> query = builder.createQuery(Role.class);
            Root<Role> root = query.from(Role.class);
            query.select(root).where(builder.equal(root.get("name"), name.trim()));
            Query q = entityManager.createQuery(query);
            List<Role> roles = new ArrayList<>();
            roles = q.getResultList();

            return roles.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public RoleDtoResponse getRole() throws Exception {
        RoleDtoResponse roleDtoResponse=new RoleDtoResponse();
        roleDtoResponse.setResponse("failed");

        try{
            List<Role> roles= (List<Role>) roleRepository.findAll();
            roleDtoResponse.setRoleDtos(roleMapper.convertToDto(roles));
            roleDtoResponse.setResponse("success");
            return roleDtoResponse;
        }catch (Exception e){
            e.printStackTrace();
        }

        return roleDtoResponse;
    }

    @Override
    @Transactional
    public RoleDtoResponse initRoles() throws Exception {
        try{
            List<Role> role= (List<Role>) roleRepository.findAll();
            if(role.size()==0){
                //System.out.println("R3: "+gson.toJson(role));
                List<Role> roles=new ArrayList<>();
                roles.add(new Role("ROLE_ADMIN"));
                roles.add(new Role("ROLE_USER"));
                roles.add(new Role("ROLE_PHARMACY"));

                roleRepository.saveAll(roles);
            }else{
                //System.out.println("R2: "+gson.toJson(role));
            }
            /*if(role.getResponse().equalsIgnoreCase("success")){

            }else{
                System.out.println("R: "+gson.toJson(role));
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public RoleDtoResponse addRole(RoleDto role) throws Exception {
        return null;
    }
}
