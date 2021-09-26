package org.guzman.despachalo.adapter.persistence.modules.company;

import org.guzman.despachalo.core.company.domain.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ruc", source = "ruc")
    @Mapping(target = "businessName", source = "businessName")
    Company toCompany(CompanyEntity companyEntity);
}
