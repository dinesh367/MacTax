package com.mactec.mactax.config;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDetails {

    private HttpStatus statusCode;
    private List<String> messages;
    private Date timestamp;
    private String debugMessage;

    public ErrorDetails(HttpStatus statusCode, List<String> message, Date timestamp, String debugMessage) {
        super();
        this.statusCode = statusCode;
        this.messages = message;
        this.timestamp = timestamp;
        this.debugMessage = debugMessage;
    }
}
