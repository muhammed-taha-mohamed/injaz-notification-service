/*
 * Copyright (c)  Injaz Company
 * @Author : Mohamed Taha
 * @Date: 11/1/2023
 */

package com.trust.error.Handelars;


import com.trust.dto.ResponsePayload;
import com.trust.error.exceptions.AbstractTrustException;
import com.trust.error.exceptions.AbstractUnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.ServletException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@ControllerAdvice
public class controllerAdvice{




    public ResponseEntity<ResponsePayload> error(Integer code, HttpStatus status, Exception e, String msg) {
        log.error("error message :"+e.getMessage());
        Map<String,String> content = new HashMap<>();
        content.put("errorMessage",msg);
        return  ResponseEntity.status(status).body(
                ResponsePayload.builder()
                        .date(LocalDateTime.now())
                        .error(content)
                        .build()
        );
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception e, Locale locale) {
        e.printStackTrace();
        return error(INTERNAL_SERVER_ERROR.value(),INTERNAL_SERVER_ERROR, e,
                e.getMessage());
    }

    @ExceptionHandler({AbstractTrustException.class})
    protected ResponseEntity handleCustomException(AbstractTrustException e, Locale locale) {
        e.printStackTrace();
        return error(INTERNAL_SERVER_ERROR.value(),INTERNAL_SERVER_ERROR, e,
                e.getMessage());
    }

    protected ResponseEntity<ResponsePayload> handleServletException(ServletException e) {
        log.error("ServletException: " + e.getMessage());
        return error(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e, "Internal Server Error");
    }

    @ExceptionHandler({AbstractUnauthorizedException.class})
    protected ResponseEntity<ResponsePayload> handleUnauthorizedException(AbstractUnauthorizedException e) {
        log.error("AbstractUnauthorizedException: " + e.getMessage());
        return error(UNAUTHORIZED.value(), UNAUTHORIZED, e, "Unauthorized request");
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR.value(),INTERNAL_SERVER_ERROR, e,
                e.getMessage());
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handSQLInjectionBindingException(MethodArgumentNotValidException e) {
        String messageTxt = null;
        try {
            messageTxt = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }catch (Exception efd){
            efd.printStackTrace();
        }
        return error(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR,new Exception("Malicious data!!!"),
                messageTxt);

    }

}