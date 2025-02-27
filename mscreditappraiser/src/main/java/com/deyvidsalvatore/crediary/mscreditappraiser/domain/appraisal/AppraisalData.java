package com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppraisalData implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private String ssn;

    private Long income;
}
