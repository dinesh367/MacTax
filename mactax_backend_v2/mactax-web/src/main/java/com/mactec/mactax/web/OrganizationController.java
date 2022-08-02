package com.mactec.mactax.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mactec.mactax.model.dto.AdminDTO;
import com.mactec.mactax.model.dto.ServiceProviderDTO;
import com.mactec.mactax.service.OrganizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author akshayp
 *
 */
@Api(tags = "Organization Api")
@RequestMapping(value = "${organization}")
@RestController
public class OrganizationController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private OrganizationService organizationService;

    @ApiOperation(value = "sign up for super admin of organization")
    @PostMapping(value = "${organization.signup}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AdminDTO createGlobalOrganizationForSuperAdmin(@RequestBody @Valid AdminDTO adminDTO) {
        return organizationService.createGlobalOrganizationSuperAdminAndAuthority(adminDTO);
    }

    @ApiOperation(value = "create or update service provider for application")
    @PostMapping(value = "${organization.save_service_provider}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ServiceProviderDTO saveServiceProvider(@RequestBody @Valid ServiceProviderDTO serviceProviderDTO) {
        return organizationService.saveServiceProvider(serviceProviderDTO);
    }

}
