package com.sprint03.service;

import com.sprint03.exceptions.notfound.NotFoundException;
import com.sprint03.model.entity.EmployeeEntity;
import com.sprint03.model.mapper.employee.request.EmployeeRequest;
import com.sprint03.model.mapper.employee.response.EmployeeResponse;
import com.sprint03.model.mapper.employee.response.EmployeeResponseMapper;
import com.sprint03.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.sprint03.model.mapper.employee.request.EmployeeRequestMapper.toEntity;
import static com.sprint03.model.mapper.employee.response.EmployeeResponseMapper.toResponse;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest){
            return toResponse(repository.save(toEntity(employeeRequest)));
    }

    public EmployeeResponse findEmployeeID(String id) {
        EmployeeEntity employeeEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID Not Found: " + id));
        return toResponse(employeeEntity);
    }

    public void deleteEmployeeID(String id){
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID Not Found: " + id));
        repository.deleteById(id);
    }

    public void deleteEmployeesByIDs(List<String> ids){
        repository.deleteAllById(ids);
    }

    public List<EmployeeResponse> employees(){
        return repository.findAll().stream()
                .map(EmployeeResponseMapper::toResponse)
                .toList();
    }

    public EmployeeResponse changeEmployeeByID(EmployeeRequest employeeRequest, String id) {
        EmployeeEntity found = repository.findById(id).orElseThrow(() -> new NotFoundException("ID Not Found: " + id));
        found.setName(employeeRequest.getName());
        found.setBirthDate(employeeRequest.getBirthDate());
        EmployeeEntity saved = repository.save(found);
        return toResponse(saved);

    }
}
