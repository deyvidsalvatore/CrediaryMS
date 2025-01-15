package com.deyvidsalvatore.crediary.mscustomers.application.representation.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CustomerRequest implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @NotNull(message = "SSN can't be null")
    @NotBlank(message = "SSN can't be blank")
    @Size(min = 9, max = 11, message = "SSR must be nine digits or eleven with -")
    @Pattern(regexp = "^(?!000|666|9\\d{2})\\d{3}-(?!00)\\d{2}-(?!0{4})\\d{4}$|^\\d{9}$",
            message = "Invalid SSN format")
    private String ssn;

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age cannot be greater than 100")
    @Positive(message = "Age must be positive")
    private Integer age;

}
