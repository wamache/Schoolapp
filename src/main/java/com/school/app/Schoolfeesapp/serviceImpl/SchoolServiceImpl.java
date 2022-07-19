package com.school.app.Schoolfeesapp.serviceImpl;

import com.school.app.Schoolfeesapp.JWT.AdminUserDetailsService;
import com.school.app.Schoolfeesapp.JWT.JwtFilter;
import com.school.app.Schoolfeesapp.JWT.JwtUtil;
import com.school.app.Schoolfeesapp.JWT.ParentUsersDetailsService;
import com.school.app.Schoolfeesapp.POJO.School;
import com.school.app.Schoolfeesapp.constents.ParentConstants;
import com.school.app.Schoolfeesapp.dao.SchoolDao;
import com.school.app.Schoolfeesapp.service.SchoolService;
import com.school.app.Schoolfeesapp.utils.EmailUtils;
import com.school.app.Schoolfeesapp.utils.ParentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolDao schoolDao;

    @Autowired
    AdminUserDetailsService adminUsersDetailsService;

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
            if (validateSignUpMap(requestMap)) {
                School school = schoolDao.findByEmailId(requestMap.get("email"));

                if (Objects.isNull(school)) {
                    schoolDao.save(getSchoolFromMap(requestMap));
                    return ParentUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                } else {
                    return ParentUtils.getResponseEntity("Email Already Exists.", HttpStatus.BAD_REQUEST);
                }


            } else {
                return ParentUtils.getResponseEntity(ParentConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if(requestMap.containsKey("schoolCode") && requestMap.containsKey("schoolName") && requestMap.containsKey("userName") && requestMap.containsKey("email") && requestMap.containsKey("password")
                && requestMap.containsKey("contactNumber") && requestMap.containsKey("confirmPassword")) {
            return true;
        }
        if(requestMap.containsKey("password")==requestMap.containsKey("confirmPassword")){
            return true;
        }
        return false;
    }


    private School getSchoolFromMap(Map<String, String> requestMap) {
        School school = new School();
        school.setSchoolCode(requestMap.get("schoolCode"));
        school.setSchoolName(requestMap.get("schoolName"));
        school.setUserName(requestMap.get("userName"));
        school.setEmail(requestMap.get("email"));
        school.setContactNumber(requestMap.get("contactNumber"));
        school.setPassword(requestMap.get("password"));
        school.setConfirmPassword(requestMap.get("password"));
        school.setStatus("true");
        school.setRole("admin");
        return school;

    }


    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))
            );
            if(auth.isAuthenticated()){
                if(adminUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtil.generateToken(adminUsersDetailsService.getUserDetail().getEmail(),
                                    adminUsersDetailsService.getUserDetail().getRole()) + "\"}",
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


}
