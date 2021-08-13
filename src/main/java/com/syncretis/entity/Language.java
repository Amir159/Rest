package com.syncretis.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "language", schema = "publisher")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(schema = "publisher", name = "persons_languages", uniqueConstraints = {
            @UniqueConstraint(columnNames = {"person_id", "language_id"})
    },
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> personList;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public Language(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id = " + id +
                ", name = '" + name + '\'' + personList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id) && Objects.equals(name, language.name) && Objects.equals(personList, language.personList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, personList);
    }
}