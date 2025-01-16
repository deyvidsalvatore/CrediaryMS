package com.deyvidsalvatore.crediary.mscreditappraiser.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerSituation implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private CustomerData customer;
    private List<CustomerCard> cards;

}
