package com.derick.service.implemetation;

import com.derick.domain.Role;
import com.derick.domain.User;
import com.derick.dto.signupoperations.UserConfirmOtpDto;
import com.derick.dto.signupoperations.UserConfirmOtpResponse;
import com.derick.dto.signupoperations.UserSignUpDto;
import com.derick.dto.signupoperations.UserSignUpResponse;
import com.derick.mapper.UserConfirmOtpMapMapper;
import com.derick.mapper.UserSignUpMapMapper;
import com.derick.repository.IRoleRepository;
import com.derick.repository.IUserRepository;
import com.derick.service.IRoleService;
import com.derick.service.IUserService;
import com.derick.utils.AppMailer;
import com.derick.utils.RandomGenerator;
import com.google.gson.Gson;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        //entityManager.getTransaction().begin();
        Gson gson=new Gson();
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query=builder.createQuery(User.class);
            Root<User> root=query.from(User.class);
            query.select(root).where(builder.equal(root.get("Phone"),userSignUpDto.getPhone().trim()));
            Query q=entityManager.createQuery(query);
            List<User> userList=new ArrayList<>();
            userList=q.getResultList();

            System.out.println("wrY: "+gson.toJson(userList)+" User: "+gson.toJson(user));
            if(userList.size()>0){
                System.out.println("Y: "+gson.toJson(userList));
                userSignUpResponse.setResponse("User already exists.");
                return userSignUpResponse;
            }
            query.select(root).where(builder.equal(root.get("Email"),userSignUpDto.getEmail().trim()));
            q=entityManager.createQuery(query);
            userList=q.getResultList();
            System.out.println("Ydfgr: "+gson.toJson(userList));

            if(userList.size()>0){
                System.out.println("Ztyj: "+userList.size());
                userSignUpResponse.setResponse("User already exists.");

                return userSignUpResponse;
            }
            if(userList.size()==0){
                System.out.println("ytuX: "+userList.size());
                user.setDateRegistered(new Date());
                user.setRegistrationConfirmed(false);

                String st="";
                st=randomGenerator.generateRandomString(6);
                user.setOtp(st);

                user.setOtpDate(new Date());
                try{
                    Set<Role> roles = new HashSet<>();
                    roles.add(roleService.getRole("ROLE_USER"));
                    //user.setRoles(Arrays.asList(roles));
                    user.setRoles(roles);
                }catch (Exception e){
                    e.printStackTrace();
                }
                entityManager.persist(user);

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
                    }
                }
                return userSignUpResponse;
            }





        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
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
            response="failed";
            usersendOtpResponse.setResponse("failed");
            e.printStackTrace();
        }
        return usersendOtpResponse;
    }
}
