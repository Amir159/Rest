package com.syncretis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Objects;

public class DocumentDto {
    @Null(message = "ID should be null during create and update")
    private String id;

    @NotNull(message = "Expiry date should be not null")
    @Future(message = "date should not be in the past")
    private LocalDate expiryDate;

    @Null(message = "personId should be null")
    private Long personId;

    public DocumentDto() {
    }

    public DocumentDto(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public DocumentDto(String id, LocalDate expiryDate) {
        this.id = id;
        this.expiryDate = expiryDate;
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

    @JsonProperty("ExpiryDate")
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty("PersonId")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDto that = (DocumentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(expiryDate, that.expiryDate) && Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expiryDate, personId);
    }

    @Override
    public String toString() {
        return "DocumentDto{" +
                "id='" + id + '\'' +
                ", expiryDate=" + expiryDate +
                ", personId=" + personId +
                '}';
    }
}
