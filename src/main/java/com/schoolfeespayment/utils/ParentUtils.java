package com.schoolfeespayment.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParentUtils {

    private ParentUtils() {

    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"\""+ responseMessage + "\"}", httpStatus);
    }

}
