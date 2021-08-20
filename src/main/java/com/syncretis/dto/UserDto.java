package com.syncretis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syncretis.entity.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

public class UserDto {
    @Null(message = "id should be null during create and update")
    private Long id;

    @NotBlank(message = "should not be blank")
    @Length(min = 4, message = "must be more than 3 characters")
    private String name;

    private Role role;

    @Pattern(regexp = "[A-Za-z ]*", message = "should contain only letters")
    @Enumerated(EnumType.STRING)
    private String city;

    @NotBlank(message = "should not be blank")
    @Length(min = 6, message = "should be more than 5 characters")

    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String name, Role role, String city, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.city = city;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public String getCity() {
        return city;
    }

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
