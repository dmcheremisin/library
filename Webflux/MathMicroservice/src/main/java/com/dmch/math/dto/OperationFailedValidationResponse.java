package com.dmch.math.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OperationFailedValidationResponse {

    private int errorCode;
    private int first;
    private int second;
    private String message;

}
