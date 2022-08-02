package com.mactec.mactax.service.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mactec.mactax.config.ErrorDetails;

/**
 * 
 * @author akshayp
 *
 */
@RestControllerAdvice(basePackages = { "com.mactec.mactax" })
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, Arrays.asList(ex.getMessage()), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, errorMessages, new Date(), "");
        return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatusCode());
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ErrorDetails> handleAuthentication(AuthenticationException ex) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, Arrays.asList(ex.getMessage()), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatusCode());
    }

    @ExceptionHandler(UnAuthorisedException.class)
    public final ResponseEntity<ErrorDetails> handleUnAuthorised(UnAuthorisedException ex) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN, Arrays.asList(ex.getMessage()), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatusCode());
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public final ResponseEntity<ErrorDetails> handleResourceAlreadyExist(ResourceAlreadyExistException ex) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.CONFLICT, Arrays.asList(ex.getMessage()), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatusCode());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatusCode());
    }

    @ExceptionHandler(NoContentException.class)
    public final ResponseEntity<ErrorDetails> handleNoContent(NoContentException ex) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NO_CONTENT, Arrays.asList(ex.getMessage()), new Date(), ex.getMessage());
        return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatusCode());
    }

}
