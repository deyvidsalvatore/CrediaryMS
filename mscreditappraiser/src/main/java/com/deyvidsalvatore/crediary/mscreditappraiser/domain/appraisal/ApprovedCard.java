package com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class ApprovedCard implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private String card;
    private String brand;
    private BigDecimal availableLimit;
}
