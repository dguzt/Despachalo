package org.guzman.despachalo.core.company.application.port.out;

import org.guzman.despachalo.core.company.domain.Employee;

import java.util.Optional;

public interface GetEmployeePort {
    Optional<Employee> getEmployeeById(Long employeeId);
    Optional<Employee> getEmployeeByEmail(String employeeEmail);
}
