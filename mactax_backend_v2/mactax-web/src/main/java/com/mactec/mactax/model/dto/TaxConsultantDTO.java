package com.mactec.mactax.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author akshayp
 */

@Data
public class TaxConsultantDTO {

    @NotNull(message = "first name should be required")
    @Size(min = 2, max = 50, message = "first name should between 2 to 50 characters")
    private String firstName;

    @NotNull(message = "last name should bse required")
    @Size(min = 2, max = 50, message = "last name should between 2 to 50 characters")
    private String lastName;

    @ApiModelProperty(notes = "Email address should be in given format 'abc@gmail.com'")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email address should be in given format 'abc@gmail.com'")
    private String email;

    @NotNull(message = "password is required")
    @Size(min = 6, max = 50, message = "password should between 6 to 50 characters")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}", message = "password must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters")
    private String password;

    @ApiModelProperty(notes = "Mobile Number should be 10 or 12 digits or default value should be null")
    @Pattern(regexp = "^(\\d{10}|\\d{12})$", message = "Mobile Number should be 10 or 12 digits")
    private String mobile;

    // domain is mandate to identify the and map {@link Product} & {@link Organization} for {@link Users(Tax Payer)}
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String domain;

}
