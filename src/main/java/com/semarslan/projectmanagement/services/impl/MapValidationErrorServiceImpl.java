package com.semarslan.projectmanagement.services.impl;

import com.semarslan.projectmanagement.services.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorServiceImpl implements MapValidationErrorService {

   public ResponseEntity<?> MApValidationService(BindingResult result) {
       if (result.hasErrors()) {
           Map<String, String> errorMap = new HashMap<>();
           for (FieldError error : result.getFieldErrors()) {
               errorMap.put(error.getField(), error.getDefaultMessage());
           }

           return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.CREATED);
       }

       return null;
   }
}
