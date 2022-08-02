package com.mactec.mactax.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author akshayp
 *
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message
     */
    public ResourceAlreadyExistException(String message) {
        super(message);
    }

}
