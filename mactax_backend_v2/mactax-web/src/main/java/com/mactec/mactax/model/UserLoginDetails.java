package com.mactec.mactax.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * @author akshayp
 *
 */
@JsonIgnoreProperties(value = { "createdBy", "updatedBy", "dateCreated", "dateUpdated" })
@Data
@Entity
public class UserLoginDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loggedInUser")
    private Users loggedInUser;

    @Column
    private String emailOtp;

    @Column
    private String smsOtp;

    @NotNull
    @Column
    private String ipAddress;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "createdBy")
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedBy")
    private Users updatedBy;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    @Column
    private Date dateCreated;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    @Column
    private Date dateUpdated;

}
