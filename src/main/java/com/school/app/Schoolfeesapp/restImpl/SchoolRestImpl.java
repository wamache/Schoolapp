package com.school.app.Schoolfeesapp.restImpl;

import com.school.app.Schoolfeesapp.constents.ParentConstants;
import com.school.app.Schoolfeesapp.rest.SchoolRest;
import com.school.app.Schoolfeesapp.service.SchoolService;
import com.school.app.Schoolfeesapp.utils.ParentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SchoolRestImpl implements SchoolRest {

    @Autowired
    SchoolService schoolService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return schoolService.signUp(requestMap);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @Override
//    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
//        return null;
//    }
}
