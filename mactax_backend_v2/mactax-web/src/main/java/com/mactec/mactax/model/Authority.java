package com.mactec.mactax.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mactec.mactax.model.enums.Status;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author akshayp
 *
 */
@Data
@Entity
@JsonIgnoreProperties(value = { "createdBy", "updatedBy", "dateCreated", "dateUpdated" })
@JsonInclude(value = Include.NON_EMPTY)
public class Authority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Size(min = 1, max = 50)
    @NotNull
    @Column
    private String role;

    @ApiModelProperty(hidden = true)
    @Enumerated
    @Column
    private Status status;

    @ApiModelProperty(hidden = true)
    @ManyToOne
    @JoinColumn(name = "createdBy")
    private Users createdBy;

    @ApiModelProperty(hidden = true)
    @ManyToOne
    @JoinColumn(name = "updatedBy")
    private Users updatedBy;

    @ApiModelProperty(hidden = true)
    @CreationTimestamp
    @Column
    private Date dateCreated;

    @ApiModelProperty(hidden = true)
    @UpdateTimestamp
    @Column
    private Date dateUpdated;

}
