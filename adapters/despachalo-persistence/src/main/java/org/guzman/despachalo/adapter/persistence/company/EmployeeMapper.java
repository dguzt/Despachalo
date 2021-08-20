package org.guzman.despachalo.adapter.persistence.company;

import org.guzman.despachalo.core.company.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "email", source = "email")
    Employee toEmployee(EmployeeEntity employeeEntity);
}
