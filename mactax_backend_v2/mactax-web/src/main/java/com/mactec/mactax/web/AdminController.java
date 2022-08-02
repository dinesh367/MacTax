package com.mactec.mactax.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author akshayp
 *
 */
@Api(tags = "Administrator Api", value = "Administrator Api")
@RequestMapping(value = "${user}")
@RestController
public class AdminController {

    protected final Log logger = LogFactory.getLog(getClass());

    @ApiOperation(value = "login for user")
    @PostMapping(value = "${user.login}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserDetails createRoles() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

}
