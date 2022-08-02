package com.mactec.mactax.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * @author akshayp
 *
 */
@Data
@Entity
@JsonIgnoreProperties(value = { "createdBy", "updatedBy", "dateCreated", "dateUpdated" })
public class UserAuthority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @NotNull(message = "user is required")
    @OneToOne
    @JoinColumn(name = "user")
    private Users user;

    @NotNull
    @OneToOne
    @JoinColumn(name = "authority")
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedBy")
    private Users updatedBy;

    @CreationTimestamp
    @Column
    private Date dateCreated;

    @UpdateTimestamp
    @Column
    private Date dateUpdated;

}
