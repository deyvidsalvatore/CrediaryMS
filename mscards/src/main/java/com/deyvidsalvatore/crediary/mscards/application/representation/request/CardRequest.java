package com.deyvidsalvatore.crediary.mscards.application.representation.request;

import com.deyvidsalvatore.crediary.mscards.domain.CardBrand;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CardRequest implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    @Size(min = 8, max = 100, message = "Name must be between 8 and 100 chars")
    private String name;

    @NotNull(message = "Brand cannot be null")
    private CardBrand brand;

    @DecimalMin(value = "0.0", inclusive = false, message = "Income must be greater than 0")
    private BigDecimal income;

    @DecimalMin(value = "0.0", inclusive = false, message = "Basic limit must be greater than 0")
    private BigDecimal basicLimit;
}
