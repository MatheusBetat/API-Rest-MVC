package com.sprint03.model.mapper.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FuncionarioResponse {
    private String id;
    private String nome;
    private LocalDate dataNascimento;
}
