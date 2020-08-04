package com.thoughtworks.rslist.component;

import com.thoughtworks.rslist.exception.CommentError;
import com.thoughtworks.rslist.exception.IndexInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IndexInvalidException.class, MethodArgumentNotValidException.class})
    public ResponseEntity handleException(Exception ex) {

        String errorMessage;
        if (ex instanceof MethodArgumentNotValidException) {
            errorMessage = "invalid param";
        }
        else{
            errorMessage = ex.getMessage();
        }

        CommentError commentError = new CommentError(errorMessage);
        return ResponseEntity.badRequest().body(commentError);
    }
}
