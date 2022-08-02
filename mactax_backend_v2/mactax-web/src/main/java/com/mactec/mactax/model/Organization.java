package com.mactec.mactax.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mactec.mactax.model.enums.Status;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author akshayp
 *
 */
@JsonIgnoreProperties(value = { "createdBy", "updatedBy", "dateCreated", "dateUpdated" })
@Data
@Entity
public class Organization {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Size(min = 1, max = 50)
    @NotNull
    @Column
    private String name;

    @Size(min = 2, max = 50)
    @NotNull
    @Column
    private String contactName;

    @Pattern(regexp = "^(\\d{10}|\\d{12})$")
    @NotNull
    @Column
    private String contactNumber;

    @Size(min = 10, max = 10)
    @NotNull
    @Column
    private String pan;

    @Size(min = 15, max = 15)
    @NotNull
    @Column
    private String gstNumber;

    @Embedded
    private OrganizationAddress address;

    @NotNull
    @Enumerated
    @Column
    private Status status;

    @ApiModelProperty(hidden = true)
    @CreationTimestamp
    @Column
    private Date dateCreated;

    @ApiModelProperty(hidden = true)
    @UpdateTimestamp
    @Column
    private Date dateUpdated;

}
