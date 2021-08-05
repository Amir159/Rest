package com.syncretis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.List;

public class DepartmentDto {

    @Null(message = "ID should be null during create and update")
    private Long id;
    @NotBlank(message = "Name should be not null")
    @Pattern(regexp = "[A-Za-z ]*", message = "should only contain letters")
    private String name;
    @Null(message = "personsId should be null")
    private List<Long> personsId;

    public DepartmentDto() {
    }

    public DepartmentDto(Long id, String name, List<Long> personsId) {
        this.id = id;
        this.name = name;
        this.personsId = personsId;
    }

    @JsonProperty("Id")
    public Long getId() {
        return id;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Persons ID")
    public List<Long> getPersonsId() {
        return personsId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonsId(List<Long> personsId) {
        this.personsId = personsId;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personsId=" + personsId +
                '}';
    }
}
