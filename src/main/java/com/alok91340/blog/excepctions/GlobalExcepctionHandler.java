package com.alok91340.blog.excepctions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alok91340.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExcepctionHandler {

    @ExceptionHandler(ResourceNotFoundExcepction.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExcepctionHandler(ResourceNotFoundExcepction resourceNotFoundExcepction){
        String message=resourceNotFoundExcepction.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return new  ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgsNotValidExcepctionHandler(MethodArgumentNotValidException ex){
         
        Map<String, String> responseMap=new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            responseMap.put(fieldName,message);
        });

        return new ResponseEntity<Map<String,String>>(responseMap,HttpStatus.BAD_REQUEST);
    }
    
}
