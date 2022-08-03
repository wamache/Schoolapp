package com.schoolfeespayment.service;


import com.schoolfeespayment.wrapper.ParentWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ParentService {


    ResponseEntity<String> signUp(Map<String, String> requestMap);
    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<ParentWrapper>> getAllParent();

    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);


}
