package com.school.app.Schoolfeesapp.serviceImpl;

import com.school.app.Schoolfeesapp.POJO.Parent;
import com.school.app.Schoolfeesapp.POJO.School;

import com.school.app.Schoolfeesapp.constents.ParentConstants;
import com.school.app.Schoolfeesapp.dao.SchoolDao;

import com.school.app.Schoolfeesapp.service.TheSchoolService;
import com.school.app.Schoolfeesapp.utils.ParentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Slf4j
@Service
public class TheSchoolServiceImpl implements TheSchoolService {

    @Autowired
    SchoolDao schoolDao;
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
        school.setStatus("true");
        school.setRole("admin");
        return school;

    }

}
