package ru.farmersregister.farmersregister.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.entity.Farmer;

@Mapper
public interface FarmerFullMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "legalForm", source = "legalForm")
  @Mapping(target = "inn", source = "inn")
  @Mapping(target = "kpp", source = "kpp")
  @Mapping(target = "ogrn", source = "ogrn")
  @Mapping(target = "registrationRegionName", source = "registrationRegion.name")
  @Mapping(target = "dateRegistration", source = "dateRegistration")
  @Mapping(target = "status", source = "status")
  FarmerFullDTO toFullDTO(Farmer farmer);

  default String getName(Long id) {
    return farmer.
  }



}
