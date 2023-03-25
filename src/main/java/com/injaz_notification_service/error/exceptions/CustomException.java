package com.injaz_notification_service.error.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
