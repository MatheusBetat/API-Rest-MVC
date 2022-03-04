package com.sprint03.model.mapper.request;

import com.sprint03.model.entity.EmployeeEntity;

public class FuncionarioRequestMapper {

    public static EmployeeEntity paraEntidadeFuncionaio(FuncionarioRequest serviceRequest){
        return EmployeeEntity.builder()
                .id(serviceRequest.getId())
                .nome(serviceRequest.getNome())
                .dataNascimento(serviceRequest.getDataNascimento())
                .build();
    }

}
