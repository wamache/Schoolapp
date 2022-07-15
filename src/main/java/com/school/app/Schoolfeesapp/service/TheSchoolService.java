package com.school.app.Schoolfeesapp.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TheSchoolService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);
}
