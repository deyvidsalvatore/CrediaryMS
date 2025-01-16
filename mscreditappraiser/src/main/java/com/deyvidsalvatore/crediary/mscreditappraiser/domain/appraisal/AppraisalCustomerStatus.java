package com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppraisalCustomerStatus implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private List<ApprovedCard> cards;
}
