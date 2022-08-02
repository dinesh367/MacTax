package com.mactec.mactax.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author akshayp
 *
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    protected final Log logger = LogFactory.getLog(getClass());

    private static final String ERROR_MESSAGE_200 = "";
    private static final String ERROR_MESSAGE_401 = "";
    private static final String ERROR_MESSAGE_403 = "";
    private static final String ERROR_MESSAGE_500 = "";
    private static final String ERROR_MESSAGE_400 = "";

    /**
     * 
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo("SBuS Documentation", "Restfull Services to Application", "0.0.1", "/termsOfServiceUrl", new Contact("adminisrator", "", ""),
                "license", "licenseUrl", Collections.emptyList());
    }

    /**
     * 
     * @return
     */
    private List<ResponseMessage> getResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message(ERROR_MESSAGE_200).build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message(ERROR_MESSAGE_401).build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message(ERROR_MESSAGE_403).build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message(ERROR_MESSAGE_500).build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message(ERROR_MESSAGE_400).responseModel(new ModelRef("error")).build());
        return responseMessages;
    }

    /**
     * 
     * @return
     */
    private List<ResponseMessage> postResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message(ERROR_MESSAGE_200).build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message(ERROR_MESSAGE_401).build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message(ERROR_MESSAGE_403).build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message(ERROR_MESSAGE_500).build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message(ERROR_MESSAGE_400).build());
        return responseMessages;
    }

    /**
     * 
     * @return
     */
    private List<ResponseMessage> patchResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message(ERROR_MESSAGE_200).build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message(ERROR_MESSAGE_401).build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message(ERROR_MESSAGE_403).build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message(ERROR_MESSAGE_500).build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message(ERROR_MESSAGE_400).build());
        return responseMessages;
    }

    /**
     * 
     * @return
     */
    private List<ResponseMessage> putResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message(ERROR_MESSAGE_200).build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message(ERROR_MESSAGE_401).build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message(ERROR_MESSAGE_403).build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message(ERROR_MESSAGE_500).build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message(ERROR_MESSAGE_400).build());
        return responseMessages;
    }

    /**
     * 
     * @return
     */
    private List<ResponseMessage> deleteResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message(ERROR_MESSAGE_200).build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message(ERROR_MESSAGE_401).build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message(ERROR_MESSAGE_403).build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message(ERROR_MESSAGE_500).build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message(ERROR_MESSAGE_400).build());
        return responseMessages;
    }

    /**
     * 
     * @return
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build().apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getResponseMessages()).globalResponseMessage(RequestMethod.POST, postResponseMessages())
                .globalResponseMessage(RequestMethod.PATCH, patchResponseMessages()).globalResponseMessage(RequestMethod.PUT, putResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, deleteResponseMessages());
    }

}
