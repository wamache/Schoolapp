package com.school.app.Schoolfeesapp.serviceImpl;


import com.google.common.base.Strings;
import com.school.app.Schoolfeesapp.JWT.JwtFilter;
import com.school.app.Schoolfeesapp.JWT.JwtUtil;
import com.school.app.Schoolfeesapp.JWT.ParentUsersDetailsService;
import com.school.app.Schoolfeesapp.POJO.Parent;
import com.school.app.Schoolfeesapp.constents.ParentConstants;
import com.school.app.Schoolfeesapp.dao.ParentDao;
import com.school.app.Schoolfeesapp.service.ParentService;

import com.school.app.Schoolfeesapp.utils.EmailUtils;
import com.school.app.Schoolfeesapp.utils.ParentUtils;
import com.school.app.Schoolfeesapp.wrapper.ParentWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    ParentDao parentDao;

    @Autowired
    ParentUsersDetailsService parentUsersDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    EmailUtils emailUtils;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);

        try {
            if(validateSignUpMap(requestMap)) {
                Parent parent = parentDao.findByEmailId(requestMap.get("email"));

                if(Objects.isNull(parent)) {
                   parentDao.save(getParentFromMap(requestMap));
                    return ParentUtils.getResponseEntity( "Successfully Registered.", HttpStatus.OK);
                }
                else {
                    return ParentUtils.getResponseEntity( "Email Already Exists.", HttpStatus.BAD_REQUEST);
                }


            }
            else {
                return ParentUtils.getResponseEntity(ParentConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if(requestMap.containsKey("firstName") && requestMap.containsKey("lastName") && requestMap.containsKey("userName") && requestMap.containsKey("email") && requestMap.containsKey("idNumber") && requestMap.containsKey("password")
                && requestMap.containsKey("contactNumber")) {
            return true;
        }
        return false;
    }


    private Parent getParentFromMap(Map<String, String> requestMap) {
        Parent parent = new Parent();
        parent.setFirstName(requestMap.get("firstName"));
        parent.setLastName(requestMap.get("lastName"));
        parent.setUserName(requestMap.get("userName"));
        parent.setEmail(requestMap.get("email"));
        parent.setIdNumber(requestMap.get("idNumber"));
        parent.setContactNumber(requestMap.get("contactNumber"));
        parent.setPassword(requestMap.get("password"));
        parent.setStatus("true");
        parent.setRole("parent");
        return parent;

    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))
            );
            if(auth.isAuthenticated()){
                if(parentUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtil.generateToken(parentUsersDetailsService.getUserDetail().getEmail(),
                                    parentUsersDetailsService.getUserDetail().getRole()) + "\"}",
                            HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval."+"\"}",
                            HttpStatus.BAD_REQUEST);

                }
            }

        }catch (Exception ex){
            log.error("{}", ex);
        }

        return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}",
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<ParentWrapper>> getAllParent() {
        try{
            if(jwtFilter.isAdmin()){
                return new ResponseEntity<>(parentDao.getAllParent(), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }


        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin()){
                Optional<Parent> optional= parentDao.findById(Integer.parseInt(requestMap.get("id")));
                if(!optional.isEmpty()){
                    parentDao.updateStatus( requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return ParentUtils.getResponseEntity("User Status Updated  Successfully", HttpStatus.OK);

                }
                else{
                    return ParentUtils.getResponseEntity("User id Doesn't exist", HttpStatus.OK);
                }


            } else {
                return ParentUtils.getResponseEntity(ParentConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED );
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return  ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return ParentUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try{
            Parent parent = parentDao.findByEmail(requestMap.get("email"));
            if(!Objects.isNull(parent) && !Strings.isNullOrEmpty(parent.getEmail())){
                emailUtils.forgotMail(parent.getEmail(), "Credentials by admin",parent.getPassword());
                
            return ParentUtils.getResponseEntity("Check your mail for Credentials.", HttpStatus.OK);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return  ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
