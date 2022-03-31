package com.sprint03.model.mapper.employee.request;

import com.sprint03.model.entity.EmployeeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeRequestMapper {

    public static EmployeeEntity toEntity(EmployeeRequest serviceRequest){
        return EmployeeEntity.builder()
                .name(serviceRequest.getName())
                .birthDate(serviceRequest.getBirthDate())
                .build();
    }

}
