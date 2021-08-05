package com.syncretis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

public class PersonDto {

    @Null(message = "id should be null during create and update")
    private Long id;
    @NotBlank(message = "should be not blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "should only contain letters")
    private String firstName;
    @NotBlank(message = "should be not blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "should only contain letters")
    private String secondName;
    @Past(message = "should not be in the future")
    private LocalDate birthday;
    @NotBlank(message = "should be not blank")
    private String departmentName;
    @NotBlank(message = "should be not blank")
    private List<String> languagesNames;
    @NotBlank(message = "should be not blank")
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
