package com.mactec.mactax.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mactec.mactax.model.enums.SmsProvider;
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
public class ServiceProvider {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @NotNull
    @Column
    private String emailHostName;

    @NotNull
    @Column
    private String emailHostPort;

    @NotNull
    @Column
    private String emailAuthUser;

    @NotNull
    @Column
    private String emailAuthPassword;

    @NotNull
    @Column
    private String emailHostType;

    @NotNull
    @Column
    private String emailAuthType;

    @NotNull
    @Column
    private String emailTrasportProtocol;

    @Column
    private boolean emailTrasportAuth;

    @Column
    private boolean emailDebug;

    @NotNull
    @Column
    private String emailSenderName;

    @NotNull
    @Enumerated
    @Column
    private SmsProvider smsProvider;

    @NotNull
    @Column
    private String smsHost;

    @Column
    private String smsSenderId;

    @Column
    private String smsSenderPassword;

    @Column
    private String smsSenderName;

    @Column
    private String smsAwsMaxPrice;

    @ApiModelProperty(hidden = true)
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
