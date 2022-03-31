package com.sprint03.controller;

import com.sprint03.exceptions.notfound.NotFoundException;
import com.sprint03.model.mapper.employee.request.EmployeeRequest;
import com.sprint03.model.mapper.employee.response.EmployeeResponse;
import com.sprint03.service.EmployeeService;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

//@DirtiesContext
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService service;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    @DisplayName("Deve procurar todos funcionarios com sucesso.")
    void shouldShowAllEmployee() {

        List<EmployeeResponse> expect = new ArrayList<>();

        doReturn(expect)
                .when(this.service).employees();

        List<EmployeeResponse> actual = this.controller.showAllEmployee();
        //Assertions.assertNotNull(actual);
        Assertions.assertEquals(expect, actual);

        Mockito.verify(this.service, Mockito.atLeastOnce()).employees();

    }

    @Test
    @DisplayName("Deve procurar um funcionario pelo id com sucesso.")
    void shouldShowEmployeeByID() {

        String id = "1";

        EmployeeResponse expect = EmployeeResponse.builder()
                .id("1")
                .name("Matheus Goulart Betat")
                .birthDate(LocalDate.parse("1997-04-10"))
                .build();

        doReturn(expect)
                .when(this.service).findEmployeeID(id);

        EmployeeResponse actual = this.controller.showEmployeeByID(id);

        Assertions.assertEquals(expect.getId(), actual.getId());
        Assertions.assertEquals(expect.getName(), actual.getName());

        Mockito.verify(this.service, Mockito.atLeastOnce()).findEmployeeID(id);
    }

    @Test
    void shouldNotShowEmployeeByInvalidId() throws NotFoundException {

        String id = null;
        //this.exceptionRule.expect(NotFoundException.class);
        this.exceptionRule.expectMessage("ID Not Found: " + id);

        doThrow(NotFoundException.class)
                .when(this.service).findEmployeeID(id);

        Assertions.assertThrows(NotFoundException.class, () -> this.service.findEmployeeID(id));
        //this.controller.showEmployeeByID(id);
        //Mockito.verify(this.service, Mockito.atLeastOnce()).findEmployeeID(id);

    }

    @Test
    @DisplayName("Deve criar um funcionario com sucesso.")
    void shouldRegisterEmployee() {

        EmployeeResponse expect = EmployeeResponse.builder()
                .id("1")
                .name("Matheus Goulart Betat")
                .birthDate(LocalDate.parse("1997-04-10"))
                .build();

        EmployeeRequest request = EmployeeRequest.builder()
                .name("Matheus Goulart Betat")
                .birthDate(LocalDate.parse("1997-04-10"))
                .build();

        doReturn(expect)
                .when(this.service).createEmployee(request);

        EmployeeResponse actual = this.controller.registerEmployee(request);

        Assertions.assertEquals(expect.getName(), actual.getName());

        Mockito.verify(this.service, Mockito.atLeastOnce()).createEmployee(request);

    }

    @Test
    @DisplayName("Deve alterar um funcionario pelo id com sucesso.")
    void shouldChangeEmployeeByID() {

        String id = "1";

        EmployeeResponse expect = EmployeeResponse.builder()
                .id("1")
                .name("Matheus Goulart Betat")
                .birthDate(LocalDate.parse("1997-04-10"))
                .build();

        EmployeeRequest request = EmployeeRequest.builder()
                .name("Matheus Goulart Betat")
                .birthDate(LocalDate.parse("1997-04-10"))
                .build();

        doReturn(expect)
                .when(this.service).changeEmployeeByID(request, id);

        EmployeeResponse actual = this.controller.changeEmployeeByID(request, id);

        Assertions.assertEquals(expect.getId(), actual.getId());

        Mockito.verify(this.service, Mockito.atLeastOnce()).changeEmployeeByID(request, id);
    }

    @Test
    @DisplayName("Deve deletar um funcionario pelo id com sucesso.")
    void shouldDeleteEmployeeByID() {

        String id = "1";

        doNothing()
                .when(this.service).deleteEmployeeID(id);

        this.controller.deleteEmployeeByID(id);

        Mockito.verify(this.service, Mockito.atLeastOnce()).deleteEmployeeID(id);
    }

    @Test
    @DisplayName("Deve deletar um ou mais funcionarios pelos ids com sucesso.")
    void shouldDeleteEmployeesByIDs() {

        List<String> ids = new ArrayList<>();

        doNothing()
                .when(this.service).deleteEmployeesByIDs(ids);

        this.controller.deleteEmployeesByIDs(ids);

        Mockito.verify(this.service, Mockito.atLeastOnce()).deleteEmployeesByIDs(ids);
    }
}