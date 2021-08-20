package org.guzman.despachalo.core.company.application.port.in;

import org.guzman.despachalo.core.company.domain.Employee;

public interface GetEmployeeUseCase {
    Employee execute(String email);
    Employee execute(Long employeeId);
}
