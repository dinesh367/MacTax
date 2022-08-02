package com.mactec.mactax.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.mactec.mactax.config.Views;
import com.mactec.mactax.model.enums.Status;

import lombok.Data;

/**
 * 
 * @author akshayp
 *
 */
@JsonIgnoreProperties(value = { "username", "password", "enabled", "decryptPassword", "createdBy", "updatedBy", "dateCreated", "dateUpdated" })
@Data
// @Entity
public class Payment {

    @JsonView(Views.Public.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @JsonView(Views.Public.class)
    @Size(min = 1, max = 50)
    @NotNull
    @Column
    private String firstName;

    @JsonView(Views.Public.class)
    @Size(min = 1, max = 50)
    @Column
    private String lastName;

    @JsonView(Views.Private.class)
    @Column
    private String username;

    @JsonView(Views.Private.class)
    @Column
    private String password;

    @JsonView(Views.Private.class)
    @Column
    private String decryptPassword;

    @JsonView(Views.Public.class)
    @Size(min = 1, max = 254)
    @Email
    @Column
    private String email;

    @JsonView(Views.Public.class)
    @Pattern(regexp = "^(\\d{10}|\\d{12})$")
    @Column
    private String mobile;

    @JsonView(Views.Public.class)
    @Pattern(regexp = "^(\\d{6})$")
    @Column
    private String emailOtp;

    @JsonView(Views.Public.class)
    @Pattern(regexp = "^(\\d{6})$")
    @Column
    private String smsOtp;

    @NotNull
    @Enumerated
    @Column
    private Status status;

    @JsonView(Views.Public.class)
    @Column
    private boolean enabled;

    @JsonView(Views.Public.class)
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    private Product product;

    @JsonView(Views.Public.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "organization")
    private Organization organization;

    @JsonView(Views.Private.class)
    @ManyToOne
    @JoinColumn(name = "createdBy")
    private Payment createdBy;

    @JsonView(Views.Private.class)
    @ManyToOne
    @JoinColumn(name = "updatedBy")
    private Payment updatedBy;

    @JsonView(Views.Private.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    @Column
    private Date dateCreated;

    @JsonView(Views.Private.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    @Column
    private Date dateUpdated;

}
