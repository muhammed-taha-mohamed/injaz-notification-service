package com.trust.error.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class AbstractTrustException extends RuntimeException {

    public AbstractTrustException(String message) {
        super(message);
    }
}
