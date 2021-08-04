package com.syncretis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDate;

public class DocumentDto {

    @Null(message = "ID should be null during create and update")
    private String id;
    /*@Future(message = "Expiry date don't be in past")*/
//    @NotBlank(message = "Expiry date should be not null")
    private LocalDate expiryDate;
    @Null(message = "personId should be null")
    private Long personId;

    public DocumentDto() {
    }

    public DocumentDto(String id, LocalDate expiryDate, Long personId) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.personId = personId;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Expiry date")
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty("Person id")
    public Long getPersonId() {
        return personId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
