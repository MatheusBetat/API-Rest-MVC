package com.sprint03.model.mapper.request;

import com.sprint03.model.entity.EmployeeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
