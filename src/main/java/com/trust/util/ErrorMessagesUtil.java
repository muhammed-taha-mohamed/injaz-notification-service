package com.trust.util;

import com.trust.enummeration.AcceptHeaderLanguage;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Random;

import static com.trust.enummeration.AcceptHeaderLanguage.en;

@Service
@AllArgsConstructor
public class ErrorMessagesUtil {
    private final HttpServletRequest servletRequest;
    private final MessageSource messageSource;
    public String getErrorMessage (String code) {
       return messageSource.getMessage(code, null,
               new Locale(servletRequest.getHeader("Accept-Language") != en.name() ?
                       AcceptHeaderLanguage.ar.toString() : servletRequest.getHeader("Accept-Language")
               ));
    }


}
