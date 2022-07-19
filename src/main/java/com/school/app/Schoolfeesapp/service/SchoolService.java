package com.school.app.Schoolfeesapp.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SchoolService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);
}
