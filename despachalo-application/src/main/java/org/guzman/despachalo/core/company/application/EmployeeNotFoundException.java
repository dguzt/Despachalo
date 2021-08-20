package org.guzman.despachalo.core.company.application;

import lombok.Getter;
import org.guzman.despachalo.commons.errors.NotFoundException;

@Getter
public class EmployeeNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "COM_001";
    private final String message;
    private final Object data = null;

    public EmployeeNotFoundException(String employeeEmail) {
        super();
        this.message = String.format("Employee not found with email: %s", employeeEmail);
    }

    public EmployeeNotFoundException(Long employeeId) {
        super();
        this.message = String.format("Employee not found with id: %d", employeeId);
    }
}
