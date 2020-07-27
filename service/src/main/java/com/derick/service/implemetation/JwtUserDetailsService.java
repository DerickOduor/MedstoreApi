package com.derick.service.implemetation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.derick.domain.Role;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @PersistenceContext
    EntityManager entityManager;

    /*@Autowired
    Gson gson;*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {

        String found="";

        com.derick.domain.User user=null;
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<com.derick.domain.User> query=builder.createQuery(com.derick.domain.User.class);
            Root<com.derick.domain.User> root=query.from(com.derick.domain.User.class);
            query.select(root).where(builder.equal(root.get("Phone"),identity.trim()));
            Query q=entityManager.createQuery(query);
            List<com.derick.domain.User> userList=new ArrayList<>();
            userList=q.getResultList();
            System.out.println("LI2: "+userList.size());
            if(!userList.isEmpty()){
                user=userList.get(0);

                found="phone";
            }else{
                query.select(root).where(builder.equal(root.get("Email"),identity.trim()));
                q=entityManager.createQuery(query);
                userList=new ArrayList<>();
                userList=q.getResultList();
                System.out.println("LI: "+userList.size());
                if(!userList.isEmpty()){
                    user=userList.get(0);

                    found="email";
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("USER: "+gson.toJson(user));
        if(user!=null)
        {
            if(found.equalsIgnoreCase("email")){
                if(user.getEmail().equalsIgnoreCase(identity.trim())){
                    return new User(identity, user.getPassword(),
                            getAuthorities(user)/*new ArrayList<>()*/);
                }
            }else if(found.equalsIgnoreCase("phone")){
                if(user.getPhone().equalsIgnoreCase(identity.trim())){
                    return new User(identity, user.getPassword(),
                            getAuthorities(user)/*new ArrayList<>()*/);
                }
            }
            if(!user.isRegistrationConfirmed()){

            }
            return new User(identity, user.getPassword(),
                    new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found with username: " + identity);
        }

        /*System.out.println("FH");
        if ("javainuse".equals(identity)) {
            System.out.println("FH efr: "+identity);
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            System.out.println("F feH: "+identity);
            throw new UsernameNotFoundException("User not found with username: " + identity);
        }*/
    }

    public Collection<? extends GrantedAuthority> getAuthorities(com.derick.domain.User user)
    {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        try{
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            //System.out.println("ROLES: "+gson.toJson(roles));
        }catch (Exception e){
            e.printStackTrace();
        }

        return authorities;
    }
}
