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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.mactec.mactax.config.Views;
import com.mactec.mactax.model.enums.ProductItemType;
import com.mactec.mactax.model.enums.Status;

import lombok.Data;

/**
 * 
 * @author akshayp
 *
 */
@JsonIgnoreProperties(value = { "createdBy", "updatedBy", "dateCreated", "dateUpdated" })
@Data
@Entity
public class ProductItem {

    @JsonView(Views.Public.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Size(min = 1, max = 50)
    @NotNull
    @Column
    private String name;

    @Size(min = 1, max = 50)
    @NotNull
    @Column
    private ProductItemType productItemType;

    @NotNull
    @Column
    private Double price;

    @NotNull
    @Enumerated
    @Column
    private Status status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

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
