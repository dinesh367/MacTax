package com.mactec.mactax.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author akshayp
 */

@Data
public class AdminDTO {

    @NotNull(message = "first name should be required")
    @Size(min = 2, max = 50, message = "first name should between 2 to 50 characters")
    private String firstName;

    @NotNull(message = "last name should be required")
    @Size(min = 2, max = 50, message = "last name should between 2 to 50 characters")
    private String lastName;

    @NotNull(message = "user name is required")
    @Size(min = 2, max = 50, message = "username should between 2 to 50 characters")
    private String userName;

    @NotNull(message = "password is required")
    @Size(min = 6, max = 50, message = "password should between 6 to 50 characters")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}", message = "password must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters")
    private String password;

    @ApiModelProperty(notes = "Email address should be in given format 'abc@gmail.com'")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email address should be in given format 'abc@gmail.com'")
    private String email;

    @ApiModelProperty(notes = "Mobile Number should be 10 or 12 digits or default value should be null")
    @Pattern(regexp = "^(\\d{10}|\\d{12})$", message = "Mobile Number should be 10 or 12 digits")
    private String mobile;

    @NotNull(message = "Organization Name should be required")
    @Size(min = 2, max = 50, message = "Organization Name should between 2 to 50 characters")
    private String organizationName;

    @NotNull(message = "Contact Name is required")
    @Size(min = 2, max = 50, message = "Contact Name is should be between 2 to 50 characters")
    private String contactName;

    @ApiModelProperty(required = true, notes = "Contact Number should be 10 or 12 digits")
    @NotNull(message = "Contact Number is required")
    @Pattern(regexp = "^(\\d{10}|\\d{12})$", message = "Contact Number should be 10 or 12 digits")
    private String contactNumber;

    @ApiModelProperty(notes = "Pan Card should be in given format 'ABCDE1234F'")
    @NotNull(message = "PAN Number should be required")
    @Size(min = 10, max = 10, message = "PAN Number should be 10 characters")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Pan Card should be in given format 'ABCDE1234F'")
    private String pan;

    @ApiModelProperty(notes = "GST Number should be in given format '23AAAAA0000A1ZA'")
    @NotNull(message = "GST Number should be required")
    @Size(min = 15, max = 15, message = "GST Number should be 15 characters")
    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}[A-Z][0-9A-Z]{1}$", message = "GST Number should be in given format '23AAAAA0000A1ZA'")
    private String gstNumber;

    @NotNull(message = "line1 is required in organization address")
    @Size(min = 2, max = 50, message = "line1 is should be between 2 to 50 characters")
    private String line1;

    @Size(min = 2, max = 50, message = "line2 is should be between 2 to 50 characters")
    private String line2;

    @NotNull(message = "city is required in organization address")
    @Size(min = 2, max = 50, message = "city is should be between 2 to 50 characters")
    private String city;

    @NotNull(message = "state is required in organization address")
    @Size(min = 2, max = 50, message = "state is should be between 2 to 50 characters")
    private String state;

    @ApiModelProperty(notes = "Pincode should be 6 Digits")
    @NotNull(message = "pincode is required in organization address")
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode should be 6 Digits")
    private String pincode;

    @NotNull(message = "country is required in organization address")
    @Size(min = 2, max = 50, message = "country is should be between 2 to 50 characters")
    private String country;

    @Size(min = 2, max = 50, message = "productName is should be between 2 to 50 characters")
    @NotNull(message = "productName is required")
    private String productName;

    @Size(min = 2, max = 255, message = "Logo should should between 2 to 255 characters")
    private String logo;

    @NotNull(message = "domain is required")
    @Size(min = 2, max = 255, message = "Website Url should between 2 to 255 characters")
    private String domain;

}
