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
import com.fasterxml.jackson.annotation.JsonView;
import com.mactec.mactax.config.Views;

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
public class OrderItemMapping {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "id")
    private Orders order;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "productItem")
    private ProductItem productItem;

    @ApiModelProperty(hidden = true)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "createdBy")
    private Users createdBy;

    @ApiModelProperty(hidden = true)
    @ManyToOne
    @JoinColumn(name = "updatedBy")
    private Users updatedBy;

    @ApiModelProperty(hidden = true)
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    @Column
    private Date dateCreated;

    @ApiModelProperty(hidden = true)
    @JsonView(Views.Private.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    @Column
    private Date dateUpdated;

}
