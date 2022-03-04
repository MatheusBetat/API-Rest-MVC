package com.sprint03.model.mapper.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeResponse {
    private String id;
    private String name;
    private LocalDate birthDate;
}
