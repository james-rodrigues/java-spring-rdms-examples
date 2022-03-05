package net.database.example.r2dbc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.database.example.r2dbc.models.Employee;
import net.database.example.r2dbc.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository repository;

    @Override
    public Mono<Employee> findEmployeeById(final Long id) {
        return repository.findById(id)
                .doOnSuccess(e -> log.info("Employee fetch with id: {}", e.getId()));
    }

    @Override
    public Flux<Employee> fetchAllEmployees() {
        return repository.findAll()
                .log("All employee data fetched");
    }

    @Override
    public Mono<Employee> createEmployee(final Employee employee) {
        return repository.save(employee)
                .doOnSuccess(e -> log.info("Employee created with id: {}", e.getId()));
    }

    @Override
    public Mono<Employee> updateEmployee(final Long id, final Employee employee) {
        return repository.findById(id)
                .doOnError(Throwable::printStackTrace)
                .map(emp -> Employee.builder()
                    .id(emp.getId())
                    .position(!Objects.isNull(employee.getPosition()) ? employee.getPosition(): emp.getPosition())
                    .department(!Objects.isNull(employee.getDepartment()) ? employee.getDepartment(): emp.getDepartment())
                    .name(!Objects.isNull(employee.getName()) ? employee.getName(): emp.getName())
                    .dateOfJoining(!Objects.isNull(employee.getDateOfJoining()) ? employee.getDateOfJoining(): emp.getDateOfJoining())
                    .build())
                .flatMap(repository::save)
                .doOnSuccess(e -> log.info("Employee updated with id: {}", e.getId()));
    }

    @Override
    public Mono<Void> deleteEmployeeById(Long id) {
        return repository.deleteById(id)
                .doOnSuccess(e -> log.info("Employee deleted with id: {}", id));
    }
}
