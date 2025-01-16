package com.deyvidsalvatore.crediary.mscreditappraiser.domain.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class RequestCardEmissionData implements Serializable {

    @Serial  private static final long serialVersionUID = 1L;

    private Long cardId;
    private String ssn;
    private String address;
    private BigDecimal freeLimit;

}
