package com.school.app.Schoolfeesapp.restImpl;

import com.school.app.Schoolfeesapp.constents.ParentConstants;
import com.school.app.Schoolfeesapp.rest.ParentRest;
import com.school.app.Schoolfeesapp.rest.TheSchoolRest;
import com.school.app.Schoolfeesapp.service.TheSchoolService;
import com.school.app.Schoolfeesapp.utils.ParentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class TheSchoolRestImpl implements TheSchoolRest {

    @Autowired
    TheSchoolService theSchoolService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return theSchoolService.signUp(requestMap);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
