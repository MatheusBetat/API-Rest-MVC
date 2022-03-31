package com.sprint03.model.mapper.employee.response;

import com.sprint03.model.entity.EmployeeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeResponseMapper {

    public static EmployeeResponse toResponse(EmployeeEntity employeeEntity){
        return EmployeeResponse.builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .birthDate(employeeEntity.getBirthDate())
                .build();
    }

}
