package com.mactec.mactax.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author akshayp
 *
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message
     * @param statusCode
     */
    public AuthenticationException(String message) {
        super(message);
    }

}
