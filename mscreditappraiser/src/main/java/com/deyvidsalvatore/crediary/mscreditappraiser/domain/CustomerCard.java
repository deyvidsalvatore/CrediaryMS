package com.deyvidsalvatore.crediary.mscreditappraiser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerCard implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private String name;
    private String brand;
    private BigDecimal limit;
}
