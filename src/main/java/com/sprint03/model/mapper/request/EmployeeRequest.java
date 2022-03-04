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
public class FuncionarioRequest {

    private String id;
    @NotNull
    @NotBlank()
    @Size(min = 20, max = 120, message = "Nome entre 20-120 caracteres.")
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
}
//todo ver cenarios q utilizo id no request