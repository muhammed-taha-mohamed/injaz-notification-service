/*
 * Copyright (c)  Injaz Company
 * @Author : Mohamed Taha
 * @Date: 11/1/2023
 */

package com.injaz_notification_service.error.Handelars;


import com.injaz_notification_service.dto.ResponseDto.ResponsePayload;
import com.injaz_notification_service.error.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity handleCustomException(CustomException e, Locale locale) {
        e.printStackTrace();
        return error(INTERNAL_SERVER_ERROR.value(),INTERNAL_SERVER_ERROR, e,
                e.getMessage());
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