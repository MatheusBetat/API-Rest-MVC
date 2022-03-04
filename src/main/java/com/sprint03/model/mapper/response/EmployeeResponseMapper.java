package com.sprint03.model.mapper.response;

import com.sprint03.model.entity.EmployeeEntity;

public class FuncionarioResponseMapper {

    public static FuncionarioResponse paraFuncionarioResponse(EmployeeEntity employeeEntity){
        return FuncionarioResponse.builder()
                .id(employeeEntity.getId())
                .nome(employeeEntity.getNome())
                .dataNascimento(employeeEntity.getDataNascimento())
                .build();
    }

}
