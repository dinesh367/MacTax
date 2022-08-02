package com.mactec.mactax.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mactec.mactax.model.dto.TaxConsultantDTO;
import com.mactec.mactax.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author akshayp
 *
 */
@Api(tags = "Tax Consultant Api", value = "Tax Consultant Api")
@RequestMapping(value = "${tax_consultant}")
@RestController
public class TaxConsultantController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "login for user")
    @PostMapping(value = "${tax_consultant.signup}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public TaxConsultantDTO registerTaxConsultant(@RequestBody @Valid TaxConsultantDTO taxConsultantDTO, HttpServletRequest request) {
        taxConsultantDTO.setDomain(request.getServerName());
        userService.registerTaxConsultant(taxConsultantDTO);
        return taxConsultantDTO;
    }

}
