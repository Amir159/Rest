package com.syncretis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class PersonDto {
    private Long id;
    private String firstName;
    private String secondName;
    private LocalDate birthday;
    private String departmentName;
    private List<String> languagesNames;
    private String documentId;

    public PersonDto() {
    }

    public PersonDto(Long id, String firstName, String secondName, LocalDate birthday, String departmentName, List<String> languagesNames, String documentId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.departmentName = departmentName;
        this.languagesNames = languagesNames;
        this.documentId = documentId;
    }

    @JsonProperty("Id")
    public Long getId() {
        return id;
    }

    @JsonProperty("First name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("Second name")
    public String getSecondName() {
        return secondName;
    }

    @JsonProperty("Birthday")
    public LocalDate getBirthday() {
        return birthday;
    }

    @JsonProperty("Department")
    public String getDepartmentName() {
        return departmentName;
    }

    @JsonProperty("Languages")
    public List<String> getLanguagesNames() {
        return languagesNames;
    }

    @JsonProperty("Document")
    public String getDocumentId() {
        return documentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLanguagesNames(List<String> languagesNames) {
        this.languagesNames = languagesNames;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", First name='" + firstName + '\'' +
                ", Second name='" + secondName + '\'' +
                ", Birthday=" + birthday +
                ", Department='" + departmentName + '\'' +
                languagesNames +
                ", Document ID='" + documentId + '\'' +
                '}';
    }
}
