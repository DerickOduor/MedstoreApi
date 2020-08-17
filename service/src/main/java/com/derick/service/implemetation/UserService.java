package com.derick.service.implemetation;

import com.derick.domain.Pharmacy;
import com.derick.domain.Role;
import com.derick.domain.User;
import com.derick.dto.signinoperations.UserSigninResponse;
import com.derick.dto.signupoperations.UserConfirmOtpDto;
import com.derick.dto.signupoperations.UserConfirmOtpResponse;
import com.derick.dto.signupoperations.UserSignUpDto;
import com.derick.dto.signupoperations.UserSignUpResponse;
import com.derick.dto.user.UserDto;
import com.derick.dto.userrole.RoleDto;
import com.derick.mapper.UserConfirmOtpMapMapper;
import com.derick.mapper.UserSignUpMapMapper;
import com.derick.mapper.user.UserMapper;
import com.derick.repository.IRoleRepository;
import com.derick.repository.IUserRepository;
import com.derick.service.IRoleService;
import com.derick.service.IUserService;
import com.derick.utils.AppMailer;
import com.derick.utils.LogFile;
import com.derick.utils.RandomGenerator;
import com.google.gson.Gson;
import javassist.NotFoundException;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserService implements IUserService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    RandomGenerator randomGenerator;

    @Autowired
    UserSignUpMapMapper userSignUpMapMapper;

    @Autowired
    UserConfirmOtpMapMapper userConfirmOtpMapMapper;

    @Autowired
    AppMailer emailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IRoleService roleService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    LogFile logFile;

    //Gson gson=new Gson();

    @Override
    @Transactional
    public User getUser(String username) throws NotFoundException {
        return null;
    }

    @Override
    @Transactional
    public User getUser(int id)throws NotFoundException  {
        try{
            return entityManager.find(User.class,id);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public User getUserByPhone(String phone) throws NotFoundException  {
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query=builder.createQuery(User.class);
            Root<User> root=query.from(User.class);
            query.select(root).where(builder.equal(root.get("Phone"),phone));
            Query q=entityManager.createQuery(query);
            List<User> userList=new ArrayList<>();
            userList=q.getResultList();

            return userList.get(0);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) throws NotFoundException {
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query=builder.createQuery(User.class);
            Root<User> root=query.from(User.class);
            query.select(root).where(builder.equal(root.get("Email"),email.trim()));
            Query q=entityManager.createQuery(query);
            List<User> userList=new ArrayList<>();
            userList=q.getResultList();
            User user=userList.get(0);

            try{
                /*CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
                CriteriaQuery<Pharmacy> criteriaQuery=criteriaBuilder.createQuery(Pharmacy.class);
                Root<Pharmacy> from=criteriaQuery.from(Pharmacy.class);
                Join<Pharmacy,User> join=from.join("users", JoinType.LEFT);
                criteriaQuery.where(criteriaBuilder.equal(join.get("id"),user.getId()));
                TypedQuery<Pharmacy> typedQuery=entityManager.createQuery(criteriaQuery);
                List<Pharmacy> pharmacies=typedQuery.getResultList();
                System.out.println("PHA:\n"+gson.toJson(pharmacies));*/
            }catch (Exception e){
                e.printStackTrace();
                logFile.error(e);
            }

            return userList.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public UserSignUpResponse saveUser(UserSignUpDto userSignUpDto) throws Exception{
        User user=new User();
        user= userSignUpMapMapper.convertToEntity(userSignUpDto);
        UserSignUpResponse userSignUpResponse=new UserSignUpResponse();
        userSignUpResponse.setResponse("failed");
        //entityManager.getTransaction().begin();
        logFile.events("User Sign up: "+(userSignUpDto.getEmail()));
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query=builder.createQuery(User.class);
            Root<User> root=query.from(User.class);
            query.select(root).where(builder.equal(root.get("Phone"),userSignUpDto.getPhone().trim()));
            Query q=entityManager.createQuery(query);
            List<User> userList=new ArrayList<>();
            userList=q.getResultList();
            logFile.events("User Sign up-: "+(userSignUpDto.getEmail()));
            //System.out.println("wrY: "+gson.toJson(userList)+" User: "+gson.toJson(user));
            if(userList.size()>0){
                //System.out.println("Y: "+gson.toJson(userList));
                userSignUpResponse.setResponse("User already exists.");
                logFile.events("User Sign up--: "+(userSignUpDto.getEmail()));
                return userSignUpResponse;
            }
            query.select(root).where(builder.equal(root.get("Email"),userSignUpDto.getEmail().trim()));
            q=entityManager.createQuery(query);
            userList=q.getResultList();
            //System.out.println("Ydfgr: "+gson.toJson(userList));
            if(userList.size()>0){
                System.out.println("Ztyj: "+userList.size());
                userSignUpResponse.setResponse("User already exists.");
                logFile.events("User Sign up---: "+(userSignUpDto.getEmail()));

                return userSignUpResponse;
            }
            if(userList.size()==0){
                System.out.println("ytuX: "+userList.size());
                try{
                    logFile.events("User Sign up----: "+(userSignUpDto.getEmail())+" Role: "+userSignUpDto.getRoles().size());
                }catch (Exception e){
                    e.printStackTrace();
                }
                user.setDateRegistered(new Date());
                user.setRegistrationConfirmed(false);
                String st="";
                st=randomGenerator.generateRandomString(6);
                user.setOtp(st);
                user.setOtpDate(new Date());
                try{
                    if(userSignUpDto.getRoles().size()==0){
                        logFile.events("User Sign up-----: "+(userSignUpDto.getEmail()));
                        Set<Role> roles = new HashSet<>();
                        roles.add(roleService.getRole("ROLE_USER"));
                        //user.setRoles(Arrays.asList(roles));
                        user.setRoles(roles);
                    }else if(userSignUpDto.getRoles().size()>0){
                        logFile.events("User Sign up has role------: "+(userSignUpDto.getEmail()));
                        Set<Role> roles = new HashSet<>();
                        for(RoleDto roleDto:userSignUpDto.getRoles()){
                            Role role=roleService.getRole(roleDto.getName());
                            roles.add(role);
                        }
                        user.setRoles(roles);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    logFile.error(e);
                }
                entityManager.persist(user);
                logFile.events("User Sign up-suc: "+(userSignUpDto.getEmail()));
                if(user.getRoles()!=null){
                    if(user.getRoles().size()>0){
                        Role role=null;
                        try{
                            //role=user.getRoles().iterator().next();
                            for(Role r:user.getRoles()){
                                if(r.getName().equalsIgnoreCase("ROLE_PHARMACY")){
                                    role=r;
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            logFile.error(e);
                        }
                        if(role!=null){
                            Pharmacy pharmacy=null;
                            try{
                                pharmacy=entityManager.find(Pharmacy.class,userSignUpDto.getPharmacy().getId());
                                Set<User> users=new HashSet<>();
                                users.add(user);
                                pharmacy.setUsers(users);
                            }catch (Exception e){
                                e.printStackTrace();
                                logFile.error(e);
                            }
                        }
                    }
                }
                userSignUpResponse.setResponse("success");
                userSignUpResponse.setUserSignUpDto(userSignUpDto);

                if (userSignUpResponse!=null){
                    try{
                        emailSender.sendSimpleMessage(user.getEmail(),"Account Opening",
                                "Dear "+user.getFirstname()+" "+user.getLastname()+
                                        ", your registration to Pharmacy has been successful. " +
                                        "Your OPT Key is "+st+"." +
                                        " Welcome to Pharmacy.");
                    }catch (Exception e){
                        e.printStackTrace();
                        logFile.error(e);
                    }
                }
                logFile.events("User Sign up==: "+(userSignUpDto.getEmail()));
                return userSignUpResponse;
            }
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }

        return userSignUpResponse;
    }

    @Override
    @Transactional
    public UserSigninResponse updateMobileToken(UserDto user) throws Exception {
        UserSigninResponse response=new UserSigninResponse();
        response.setResponse("failed");
        try{
            User user1=entityManager.find(User.class,user.getId());
            user1.setMobileToken(user.getMobileToken());
            userRepository.save(user1);
            //entityManager.co
            //entityManager.detach(user1);
            //entityManager.merge(user1);
            //entityManager.getTransaction().commit();

            response.setUserDto(userMapper.convertToDto(user1));
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
    public UserSigninResponse updateUser(UserDto user) throws Exception {
        UserSigninResponse response=new UserSigninResponse();
        response.setResponse("failed");
        try {
            User user1=entityManager.find(User.class,user.getId());
            user1.setFirstname(user.getFirstname());
            user1.setLastname(user.getLastname());

            entityManager.persist(user1);

            response.setUserDto(userMapper.convertToDto(user1));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public UserSignUpResponse resetPassword(UserSignUpDto userResetPassword) throws Exception {
        User user=userSignUpMapMapper.convertToEntity(userResetPassword);
        User userInDatabase=null;
        String response="";
        UserSignUpResponse userSignUpResponse=new UserSignUpResponse();
        try{
            userInDatabase=getUserByEmail(user.getEmail().trim());
            if(userInDatabase==null){
                userInDatabase=getUserByPhone(user.getPhone().trim());
            }

            if(userInDatabase!=null){
                userInDatabase.setPassword(passwordEncoder.encode(userResetPassword.getPassword()));

                entityManager.persist(userInDatabase);
                if (userInDatabase!=null){
                    try{
                        emailSender.sendSimpleMessage(userInDatabase.getEmail(),"PASSWORD RESET",
                                "Dear "+userInDatabase.getFirstname()+" "+userInDatabase.getLastname()+
                                        ", your password has been reset at "+
                                        new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(new Date())+
                                ". If this was not you, consider changing your password.");
                    }catch (Exception e){
                        logFile.error(e);
                        e.printStackTrace();
                    }
                }
                userSignUpResponse.setResponse("success");
                userSignUpResponse.setUserSignUpDto(userResetPassword);
                response="success";
            }else {
                response="failed";
                userSignUpResponse.setResponse("failed");
            }
        }catch (Exception e){
            logFile.error(e);
            response="failed";
            userSignUpResponse.setResponse("failed");
            e.printStackTrace();
        }
        return userSignUpResponse;
    }

    @Override
    @Transactional
    public UserConfirmOtpResponse confirmOtp(UserConfirmOtpDto userConfirmOtpDto) throws Exception {
        User user=userConfirmOtpMapMapper.convertToEntity(userConfirmOtpDto);
        User userInDatabase=null;
        String response="";
        UserConfirmOtpResponse userConfirmOtpResponse=new UserConfirmOtpResponse();
        try{
            userInDatabase=getUserByEmail(user.getEmail().trim());
            if(userInDatabase==null){
                userInDatabase=getUserByPhone(user.getPhone().trim());
            }

            if(userInDatabase!=null){
                if(userInDatabase.getOtp().trim().equalsIgnoreCase(user.getOtp().trim())){

                    long diffInMillies = Math.abs(new Date().getTime() - userInDatabase.getOtpDate().getTime());
                    long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    if(diff<24){
                        userInDatabase.setRegistrationConfirmed(true);
                        userInDatabase.setOtpConfirmed(true);
                        userInDatabase.setLocked(false);
                        entityManager.persist(userInDatabase);
                        userConfirmOtpResponse.setResponse("success");
                        userConfirmOtpResponse.setUserSignUpDto(userConfirmOtpMapMapper.convertToDto(userInDatabase));
                    }else{
                        userConfirmOtpResponse.setResponse("OTP expired");
                    }
                }
            }else {
                userConfirmOtpResponse.setResponse("failed");
            }
        }catch (Exception e){
            logFile.error(e);
            userConfirmOtpResponse.setResponse("failed");
            e.printStackTrace();
        }
        return userConfirmOtpResponse;
    }

    @Override
    @Transactional
    public UserConfirmOtpResponse sendOtp(UserConfirmOtpDto userConfirmOtpDto) throws Exception {
        User user=userConfirmOtpMapMapper.convertToEntity(userConfirmOtpDto);
        User userInDatabase=null;
        String response="";
        UserConfirmOtpResponse usersendOtpResponse=new UserConfirmOtpResponse();
        try{
            userInDatabase=getUserByEmail(user.getEmail().trim());
            if(userInDatabase==null){
                userInDatabase=getUserByPhone(user.getPhone().trim());
            }

            if(userInDatabase!=null){
                String st="";
                st=randomGenerator.generateRandomString(6);
                userInDatabase.setOtp(st);

                userInDatabase.setOtpDate(new Date());

                entityManager.persist(userInDatabase);
                if (userInDatabase!=null){
                    try{
                        emailSender.sendSimpleMessage(userInDatabase.getEmail(),"VERIFY ACCOUNT",
                                "Dear "+userInDatabase.getFirstname()+" "+userInDatabase.getLastname()+
                                        ", your Verification Code is: " +
                                        st+"." +
                                        " Enter this code to proceed.");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                usersendOtpResponse.setResponse("success");
                usersendOtpResponse.setUserSignUpDto(userConfirmOtpDto);
                response="success";
            }else {
                response="failed";
                usersendOtpResponse.setResponse("failed");
            }
        }catch (Exception e){
            logFile.error(e);
            response="failed";
            usersendOtpResponse.setResponse("failed");
            e.printStackTrace();
        }
        return usersendOtpResponse;
    }
}
