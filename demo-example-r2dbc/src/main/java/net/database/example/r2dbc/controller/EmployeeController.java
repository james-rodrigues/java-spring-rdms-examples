package net.database.example.r2dbc.controller;

import lombok.AllArgsConstructor;
import net.database.example.r2dbc.models.Employee;
import net.database.example.r2dbc.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<Mono<Employee>> createEmployee(Employee employee) {
        return new ResponseEntity<>(service.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Employee>> getEmployeeById(@PathVariable("id") long id) {
        Mono<Employee> employee = service.findEmployeeById(id);
        return new ResponseEntity<>(employee, !Objects.isNull(employee) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Flux<Employee>> findAllEmployees() {
        return new ResponseEntity<>(service.fetchAllEmployees(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Employee>> updateEmployeeDetails(@PathVariable("id") long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(service.updateEmployee(id, employee), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> deleteEmployeeById(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.deleteEmployeeById(id), HttpStatus.NO_CONTENT);
    }


}
