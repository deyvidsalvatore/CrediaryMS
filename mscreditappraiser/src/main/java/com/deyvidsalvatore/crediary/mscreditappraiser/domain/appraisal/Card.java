package com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class Card implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String brand;
    private BigDecimal income;
    private BigDecimal basicLimit;

}
