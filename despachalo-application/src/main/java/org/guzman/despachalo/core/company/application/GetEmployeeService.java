package org.guzman.despachalo.core.company.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.company.application.port.in.GetEmployeeUseCase;
import org.guzman.despachalo.core.company.application.port.out.GetEmployeePort;
import org.guzman.despachalo.core.company.domain.Employee;

@UseCase
@RequiredArgsConstructor
public class GetEmployeeService implements GetEmployeeUseCase {
    private final GetEmployeePort getEmployeePort;

    @Override
    public Employee execute(String email) {
        return getEmployeePort.getEmployeeByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(email));
    }

    @Override
    public Employee execute(Long employeeId) {
        return getEmployeePort.getEmployeeById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }
}
