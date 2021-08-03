package com.syncretis.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "document", schema = "publisher")
public class Document {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @OneToOne(mappedBy = "document", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Person person;

    public Document() {
    }

    public Document(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Document(String id, LocalDate expiryDate) {
        this.id = id;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return id;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
