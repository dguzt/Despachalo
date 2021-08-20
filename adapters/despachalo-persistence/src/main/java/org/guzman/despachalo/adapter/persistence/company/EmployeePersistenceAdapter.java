package org.guzman.despachalo.adapter.persistence.company;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.company.application.port.out.GetEmployeePort;
import org.guzman.despachalo.core.company.domain.Employee;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements GetEmployeePort {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public Optional<Employee> getEmployeeById(Long employeeId) {
        return repository
                .findById(employeeId)
                .map(mapper::toEmployee);
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String employeeEmail) {
        return repository
                .findByEmail(employeeEmail)
                .map(mapper::toEmployee);
    }
}
