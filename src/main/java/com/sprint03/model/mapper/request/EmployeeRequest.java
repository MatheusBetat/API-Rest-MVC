package com.sprint03.model.mapper.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeRequest {

    @NotNull
    @NotBlank()
    @Size(min = 20, max = 120, message = "Name with 20-120 characters.")
    private String name;
    @NotNull
    private LocalDate birthDate;
}
