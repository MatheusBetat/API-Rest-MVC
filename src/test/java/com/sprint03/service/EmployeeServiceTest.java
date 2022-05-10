package com.sprint03.service;

import com.sprint03.exceptions.notfound.NotFoundException;
import com.sprint03.model.entity.EmployeeEntity;
import com.sprint03.model.mapper.employee.request.EmployeeRequest;
import com.sprint03.model.mapper.employee.response.EmployeeResponse;
import com.sprint03.repository.EmployeeRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository repository;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    @DisplayName("Deve criar um funcionario com sucesso.")
    void shouldCreateEmployee() {

        EmployeeEntity entity =
                new EmployeeEntity("1", "Matheus Goulart Betat", LocalDate.parse("1997-04-10"));

        EmployeeResponse response = EmployeeResponse.builder()
                .id("1")
                .name("Matheus Goulart Betat")
                .birthDate(LocalDate.parse("1997-04-10"))
                .build();

        EmployeeRequest request = EmployeeRequest.builder()
                .name("Matheus Goulart Betat")
                .birthDate(LocalDate.parse("1997-04-10"))
                .build();

        doReturn(entity).when(this.repository).save(any(EmployeeEntity.class));
        EmployeeResponse actual = this.service.createEmployee(request);
        Assertions.assertEquals(entity.getName(), actual.getName());
        Mockito.verify(this.repository, Mockito.atLeastOnce()).save(any(EmployeeEntity.class));

    }

    @Test
    @DisplayName("Deve procurar um funcionario pelo id com sucesso.")
    void shouldFindEmployeeByID() {

        String id = "1";
        EmployeeEntity expect =
                new EmployeeEntity("1", "Matheus Goulart Betat", LocalDate.parse("1997-04-10"));

        doReturn(Optional.of(expect))
                .when(this.repository).findById(id);

        Optional<EmployeeResponse> actual = Optional.ofNullable(service.findEmployeeID(id));

        Assertions.assertEquals(expect.getId(), id);

        Mockito.verify(this.repository, Mockito.atLeastOnce()).findById(id);

    }

    @Test
    @Disabled
    void shouldNotShowEmployeeByInvalidId() throws NotFoundException {

        String id = null;

        EmployeeEntity expect =
                new EmployeeEntity("1", "Matheus Goulart Betat", LocalDate.parse("1997-04-10"));

        doThrow(NotFoundException.class)
                .when(this.repository).findById(id);

        this.exceptionRule.expect(NotFoundException.class);
        this.exceptionRule.expectMessage("ID Not Found: " + id);

        //Assertions.assertThrows(NotFoundException.class, () -> this.service.findEmployeeID(id));

        //this.service.findEmployeeID(id);

        Mockito.verify(this.repository, Mockito.atLeastOnce()).findById(id);
    }

    @Test
    @DisplayName("Deve deletar um funcionario pelo id com sucesso.")
    void shouldDeleteEmployeeByID() {

        String id = "1";
        EmployeeEntity entity =
                new EmployeeEntity("1", "Matheus Goulart Betat", LocalDate.parse("1997-04-10"));

        doReturn(Optional.of(entity))
                .when(this.repository).findById(id);

        this.service.deleteEmployeeID(id);

        Mockito.verify(this.repository, Mockito.atLeastOnce()).deleteById(id);
    }

    @Test
    @DisplayName("Deve deletar um ou mais funcionarios pelos ids com sucesso.")
    void shouldDeleteEmployeesByIDs() {

        List<String> ids = new ArrayList<>();

        doNothing()
                .when(this.repository).deleteAllById(ids);

        this.service.deleteEmployeesByIDs(ids);

        Mockito.verify(this.repository, Mockito.atLeastOnce()).deleteAllById(ids);

    }

    @Test
    @DisplayName("Deve listar todos funcionarios com sucesso.")
    void shouldListAllEmployees() {

        List<EmployeeResponse> response = new ArrayList<>();

        doReturn(response)
                .when(this.repository).findAll();

        List<EmployeeResponse> actual = this.service.employees();

        Assertions.assertEquals(response, actual);

        Mockito.verify(this.repository, Mockito.atLeastOnce()).findAll();
    }

    @Test
    @DisplayName("Deve alterar um funcionario pelo id com sucesso.")
    void shouldChangeEmployeeByID() {

        EmployeeEntity expect =
                new EmployeeEntity("1", "Matheus Goulart Betat", LocalDate.parse("1997-04-10"));

        String id = "1";

        EmployeeRequest request = EmployeeRequest.builder()
                .name("Matheus Goulart Betat")
                .birthDate(LocalDate.parse("1997-04-10"))
                .build();

        doReturn(Optional.of(expect))
                .when(this.repository).findById(id);
        doReturn(expect)
                .when(this.repository).save(expect);

        EmployeeResponse actual = this.service.changeEmployeeByID(request, id);

        Assertions.assertEquals(expect.getId(), id);

        Mockito.verify(this.repository, Mockito.atLeastOnce()).save(expect);

    }
}