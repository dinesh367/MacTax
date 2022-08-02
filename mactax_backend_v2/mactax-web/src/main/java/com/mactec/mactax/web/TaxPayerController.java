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

import com.mactec.mactax.model.dto.TaxPayerDTO;
import com.mactec.mactax.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author akshayp
 *
 */
@Api(tags = "Tax Payer Api", value = "Tax Payer Api")
@RequestMapping(value = "${tax_payer}")
@RestController
public class TaxPayerController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "login for user")
    @PostMapping(value = "${tax_payer.signup}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public TaxPayerDTO registerTaxConsultant(@RequestBody @Valid TaxPayerDTO taxPayerDTO, HttpServletRequest request) {
        taxPayerDTO.setDomain(request.getServerName());
        userService.registerTaxPayer(taxPayerDTO);
        return taxPayerDTO;
    }

}
