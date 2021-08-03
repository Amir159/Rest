package com.syncretis.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "person", schema = "publisher",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"document_id"})
        })

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(nullable = false)
    private LocalDate birthday;

    @ManyToOne()
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToMany()
    @JoinTable(schema = "publisher", name = "persons_languages", uniqueConstraints = {
            @UniqueConstraint(columnNames = {"person_id", "language_id"})
    },
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languageList;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "document_id")
    private Document document;

    public Person() {
    }

    public Person(String firstName, String secondName, LocalDate birthday, Department department, Document document) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.department = department;
        this.document = document;
    }

    public Person(Long id, String firstName, String secondName, LocalDate birthday, Department department, Document document) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.department = department;
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Department getDepartment() {
        return department;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public Document getDocument() {
        return document;
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

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id = " + id +
                ", firstName = '" + firstName + '\'' +
                ", secondName = '" + secondName + '\'' +
                ", birthday = " + birthday +
                ", " + department +
                '}';
    }
}
