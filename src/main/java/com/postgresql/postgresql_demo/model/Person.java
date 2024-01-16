package com.postgresql.postgresql_demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
}
