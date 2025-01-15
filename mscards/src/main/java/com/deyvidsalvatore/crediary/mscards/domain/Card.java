package com.deyvidsalvatore.crediary.mscards.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Card implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card(String name, CardBrand brand, BigDecimal income, BigDecimal basicLimit) {
        this.name = name;
        this.brand = brand;
        this.income = income;
        this.basicLimit = basicLimit;
    }
}
