package com.semarslan.projectmanagement.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface MapValidationErrorService {

    public ResponseEntity<?> MApValidationService(BindingResult result);
}
