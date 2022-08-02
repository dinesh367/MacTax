package com.mactec.mactax.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
public class Users {

    @JsonView(Views.Public.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Size(min = 1, max = 50)
    @NotNull
    @Column
    private String firstName;

    @Size(min = 1, max = 50)
    @Column
    private String lastName;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String decryptPassword;

    @Size(min = 1, max = 254)
    @Email
    @Column
    private String email;

    @Pattern(regexp = "^(\\d{10}|\\d{12})$")
    @Column
    private String mobile;

    @Pattern(regexp = "^(\\d{6})$")
    @Column
    private String emailOtp;

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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    private Product product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "organization")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedBy")
    private Users updatedBy;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    @Column
    private Date dateCreated;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    @Column
    private Date dateUpdated;

}
