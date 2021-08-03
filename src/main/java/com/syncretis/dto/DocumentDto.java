package com.syncretis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class DocumentDto {
    private String id;
    private LocalDate expiryDate;
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
