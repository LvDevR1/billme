package com.rest.billme.api.beans;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorBean {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
