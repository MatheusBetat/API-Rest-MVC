package com.sprint03.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "Funcionario")
public class Funcionario {
    @Id
    private String id;
    private String nome;
    private LocalDate dataNascimento;
}
