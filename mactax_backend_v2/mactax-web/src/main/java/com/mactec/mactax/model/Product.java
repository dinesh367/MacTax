package com.mactec.mactax.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mactec.mactax.model.enums.OtpType;

import lombok.Data;

/**
 * 
 * @author akshayp
 *
 */
@JsonIgnoreProperties(value = { "createdBy", "updatedBy", "dateCreated", "dateUpdated" })
@Data
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Size(min = 2, max = 255)
    @Column
    private String name;

    @Size(min = 2, max = 255)
    @Column
    private String logo;

    @NotNull
    @Size(min = 2, max = 255)
    @Column
    private String domain;

    @NotNull
    @Enumerated
    @Column
    private OtpType emailOtpType;

    @NotNull
    @Enumerated
    @Column
    private OtpType smsOtpType;

    @OneToOne
    @JoinColumn(name = "serviceProvider")
    private ServiceProvider serviceProvider;

    @OneToOne
    @JoinColumn(name = "organization")
    private Organization organization;

    @CreationTimestamp
    @Column
    private Date dateCreated;

    @UpdateTimestamp
    @Column
    private Date dateUpdated;

}
