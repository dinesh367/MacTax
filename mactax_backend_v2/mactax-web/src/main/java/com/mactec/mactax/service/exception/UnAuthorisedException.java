package com.mactec.mactax.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author akshayp
 *
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UnAuthorisedException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message
     * @param statusCode
     */
    public UnAuthorisedException(String message) {
        super(message);
    }

}
