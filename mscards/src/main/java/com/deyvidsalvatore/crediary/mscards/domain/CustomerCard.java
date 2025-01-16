package com.deyvidsalvatore.crediary.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity @Data @NoArgsConstructor
public class CustomerCard implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ssn;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "card_limit")
    private BigDecimal limit;
}
