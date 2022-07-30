package com.schoolfeespayment.restImpl;

import com.schoolfeespayment.JWT.JwtFilter;
import com.schoolfeespayment.POJO.Parent;
import com.schoolfeespayment.constents.ParentConstants;
import com.schoolfeespayment.dao.ParentDao;
import com.schoolfeespayment.rest.ParentRest;
import com.schoolfeespayment.service.ParentService;
import com.schoolfeespayment.utils.ParentUtils;
import com.schoolfeespayment.wrapper.ParentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ParentRestImpl implements ParentRest {

    @Autowired
    ParentService parentService;

    @Autowired
    ParentDao parentDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {

            return parentService.signUp(requestMap);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return  parentService.login(requestMap);
        } catch (Exception ex){
            ex.printStackTrace();

        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ParentWrapper>> getAllParent() {
        try{
            return parentService.getAllParent();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<ParentWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            return parentService.update(requestMap);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> checkToken() {
        try{
            return parentService.checkToken();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            Parent parentObj = parentDao.findByEmail(jwtFilter.getCurrentParent());
            if(!parentObj.equals(null)){
                if(parentObj.getPassword().equals(requestMap.get("oldPassword"))){
                    parentObj.setPassword(requestMap.get("newPassword"));
                    parentDao.save(parentObj);
                    return ParentUtils.getResponseEntity("Password Updated Successfully", HttpStatus.OK);
                }
                return ParentUtils.getResponseEntity("Incorrect OldPassword", HttpStatus.BAD_REQUEST);

            }
            return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try{
            return parentService.forgotPassword(requestMap);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ParentUtils.getResponseEntity(ParentConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
