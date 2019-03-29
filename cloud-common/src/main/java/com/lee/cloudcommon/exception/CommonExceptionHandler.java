package com.lee.cloudcommon.exception;

import com.lee.cloudcommon.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Exception.class)
    R exception(Exception e){
        return R.error(500,e.getMessage());
    }
}
