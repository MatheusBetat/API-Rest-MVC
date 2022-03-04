package com.sprint03.controller;

import com.sprint03.service.funcionario.EmployeeService;
import com.sprint03.model.mapper.request.EmployeeRequest;
import com.sprint03.model.mapper.response.EmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping({"/v1/employee"})
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> showAllEmployee(){
        return this.service.employees();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse showEmployeeByID(@PathVariable String id){
        return this.service.findEmployeeID(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse registerEmployee(@RequestBody @Valid EmployeeRequest employee) {
        return this.service.createEmployee(employee);
    }

    @PutMapping("/change/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public EmployeeResponse changeEmployeeByID(@RequestBody EmployeeRequest employee ,
                                               @PathVariable String id){
        return this.service.changeEmployeeByID(employee, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeByID(@PathVariable String id){
        this.service.deleteEmployeeID(id);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeesByIDs(@RequestParam List<String> ids){
        service.deleteEmployeesByIDs(ids);
    }

    @PostMapping("/cookies")
    @ResponseStatus(HttpStatus.CREATED)
    public String setCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("EmployeeCookie", "1");
        cookie.setMaxAge(60*60*24);
        cookie.setDomain("Localhost");
        cookie.setValue(cookie.getValue());
        response.addCookie(cookie);
        return "Cookie added!";
    }
}

