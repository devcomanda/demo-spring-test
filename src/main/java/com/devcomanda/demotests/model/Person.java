package com.devcomanda.demotests.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
