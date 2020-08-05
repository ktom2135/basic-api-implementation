package com.thoughtworks.rslist.component;

import com.thoughtworks.rslist.exception.CommonError;
import com.thoughtworks.rslist.exception.InvalidIndexException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({InvalidIndexException.class, MethodArgumentNotValidException.class})
    public ResponseEntity exceptionHandler(Exception ex){

        String errorMessage ;
        if(ex instanceof MethodArgumentNotValidException){
            errorMessage = "invalid param";
        }
        else{
            errorMessage = ex.getMessage();
        }

        CommonError commonError = new CommonError();
        commonError.setError(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonError);
    }
}
