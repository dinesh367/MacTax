package com.mactec.mactax.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mactec.mactax.model.dto.OtpDTO;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author akshaylap
 *
 */
@Setter
@Getter
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private OtpDTO otp;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.otp = objectMapper.readValue(request.getReader(), OtpDTO.class);
            request.getReader().close();
        } catch (JsonParseException e) {
            this.otp = null;
        } catch (JsonMappingException e) {
            this.otp = null;
        } catch (IOException e) {
            this.otp = null;
        }
    }

}
