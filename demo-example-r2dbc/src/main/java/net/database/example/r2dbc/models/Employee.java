package net.database.example.r2dbc.models;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Builder
public class Employee {
    @Id
    private final Long id;
    private final String name;
    private final String position;
    private final String department;
    private final LocalDate dateOfJoining;

}
