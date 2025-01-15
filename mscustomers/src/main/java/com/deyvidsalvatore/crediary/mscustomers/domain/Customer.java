package com.deyvidsalvatore.crediary.mscustomers.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity @Data @NoArgsConstructor
public class Customer implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ssn", unique = true, nullable = false)
    private String ssn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    public Customer(String ssn, String name, Integer age) {
        this.ssn = ssn;
        this.name = name;
        this.age = age;
    }
}
