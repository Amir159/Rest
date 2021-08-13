package com.syncretis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @NotNull(message = "should be not null")
    @NotEmpty(message = "should be not empty")
    private List<String> languagesNames;

    @NotBlank(message = "should be not blank")
    private String documentId;

    public PersonDto() {
    }

    public PersonDto(Long id, String firstName, String secondName, LocalDate birthday, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.departmentName = departmentName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(id, personDto.id) && Objects.equals(firstName, personDto.firstName)
                && Objects.equals(secondName, personDto.secondName)
                && Objects.equals(birthday, personDto.birthday)
                && Objects.equals(departmentName, personDto.departmentName)
                && Objects.equals(languagesNames, personDto.languagesNames)
                && Objects.equals(documentId, personDto.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, birthday, departmentName, languagesNames, documentId);
    }
}
