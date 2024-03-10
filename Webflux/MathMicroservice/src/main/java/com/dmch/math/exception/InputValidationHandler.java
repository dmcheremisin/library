package com.dmch.math.exception;

import com.dmch.math.dto.InputFailedValidationResponse;
import com.dmch.math.dto.OperationFailedValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex) {
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setInput(ex.getInput());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(OperationValidationException.class)
    public ResponseEntity<OperationFailedValidationResponse> handleException(OperationValidationException ex) {
        var response = new OperationFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setFirst(ex.getFirst());
        response.setSecond(ex.getSecond());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
