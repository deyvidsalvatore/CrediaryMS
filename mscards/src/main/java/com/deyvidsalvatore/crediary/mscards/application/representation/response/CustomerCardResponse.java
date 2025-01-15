package com.deyvidsalvatore.crediary.mscards.application.representation.response;

import com.deyvidsalvatore.crediary.mscards.domain.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCardResponse implements Serializable {

    @Serial private static final long serialVersionUID = 1L;
    private String name;
    private String brand;
    private BigDecimal freeLimit;

    public static CustomerCardResponse fromModel(CustomerCard customerCard) {
        return new CustomerCardResponse(
                customerCard.getCard().getName(),
                customerCard.getCard().getBrand().toString(),
                customerCard.getLimit()
        );
    }
}
