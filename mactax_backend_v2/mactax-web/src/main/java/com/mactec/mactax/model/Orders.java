package com.mactec.mactax.model;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mactec.mactax.config.Views;
import com.mactec.mactax.model.dto.DocumentsJson;
import com.mactec.mactax.model.enums.Status;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author akshayp
 *
 */
@JsonIgnoreProperties(value = { "createdBy", "updatedBy", "dateCreated", "dateUpdated" })
@Setter
@Getter
@Entity
public class Orders {

    @JsonView(Views.Public.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "placedBy")
    private Users placedBy;

    @Column(columnDefinition = "LONGTEXT")
    private String documents;

    @Transient
    private List<DocumentsJson> documentsJson;

    @OneToMany(mappedBy = "order")
    private List<OrderItemMapping> orderItemMapping;

    @NotNull
    @Enumerated
    @Column
    private Status status;

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
    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    @Column
    private Date dateUpdated;

    /**
     * 
     * @param documentsJson
     * @throws JsonProcessingException
     */
    public void setDocuments(List<DocumentsJson> documentsJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (!documentsJson.isEmpty()) {
            this.documents = mapper.writeValueAsString(documentsJson);
        }
    }

    /**
     * 
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public DocumentsJson getDocuments() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (this.documents != null) {
            mapper.readValue(this.documents, DocumentsJson[].class);
        }
        return null;
    }
}
