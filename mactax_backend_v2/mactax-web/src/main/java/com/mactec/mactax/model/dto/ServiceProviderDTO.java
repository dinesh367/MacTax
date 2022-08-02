package com.mactec.mactax.model.dto;

import javax.validation.constraints.NotNull;

import com.mactec.mactax.model.enums.SmsProvider;

import lombok.Data;

@Data
public class ServiceProviderDTO {

    private Integer id;

    @NotNull(message = "emailHostName is required")
    private String emailHostName;

    @NotNull(message = "emailHostPort is required")
    private String emailHostPort;

    @NotNull(message = "emailAuthUser is required")
    private String emailAuthUser;

    @NotNull(message = "emailAuthPassword is required")
    private String emailAuthPassword;

    @NotNull(message = "emailHostType is required")
    private String emailHostType;

    @NotNull(message = "emailAuthType is required")
    private String emailAuthType;

    @NotNull(message = "emailTrasportProtocol is required")
    private String emailTrasportProtocol;

    @NotNull(message = "emailTrasportAuth is required")
    private boolean emailTrasportAuth;

    @NotNull(message = "emailDebug is required")
    private boolean emailDebug;

    @NotNull(message = "emailSenderName is required")
    private String emailSenderName;

    @NotNull(message = "smsProvider is required")
    private SmsProvider smsProvider;

    @NotNull(message = "smsHost is required")
    private String smsHost;

    @NotNull(message = "smsSenderId is required")
    private String smsSenderId;

    @NotNull(message = "smsSenderPassword is required")
    private String smsSenderPassword;

    @NotNull(message = "smsSenderName is required")
    private String smsSenderName;

    @NotNull(message = "smsAwsMaxPrice is required")
    private String smsAwsMaxPrice;
}
