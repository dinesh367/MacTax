package com.mactec.mactax.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.mactec.mactax.service.LoginSecurityService;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author akshaylap
 *
 */
@Setter
@Getter
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private LoginSecurityService loginSecurityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        loginSecurityService.checkVerificationCode(authentication);
        return super.authenticate(authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
