package com.test.musinsa.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerUtil {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllException(Exception exception) {
        return handleException(exception);
    }

    public static ResponseEntity<ApiResponse<Void>> handleException(Exception exception) {
        if(exception instanceof IllegalArgumentException) {
            // 400 에러
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, exception.getMessage());
        } else if(exception instanceof NoHandlerFoundException) {
            // 404 에러
            return ResponseUtil.error(HttpStatus.NOT_FOUND, exception.getMessage());
        } else {
            // 500 에러
            return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
