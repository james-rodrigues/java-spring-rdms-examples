package net.database.example.r2dbc.service;

import net.database.example.r2dbc.models.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> findEmployeeById(Long id);
    Flux<Employee> fetchAllEmployees();
    Mono<Employee> createEmployee(Employee employee);
    Mono<Employee> updateEmployee(Long id, Employee employee);
    Mono<Void> deleteEmployeeById(Long id);
}
