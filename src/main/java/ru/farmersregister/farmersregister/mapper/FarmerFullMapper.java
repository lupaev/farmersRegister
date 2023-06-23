package ru.farmersregister.farmersregister.mapper;

import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;

@Mapper
public interface FarmerFullMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "legalForm", source = "legalForm")
  @Mapping(target = "inn", source = "inn")
  @Mapping(target = "kpp", source = "kpp")
  @Mapping(target = "ogrn", source = "ogrn")
  @Mapping(target = "registrationRegionName", source = "region.name")
  @Mapping(target = "dateRegistration", source = "dateRegistration")
  @Mapping(target = "status", source = "status")
  @Mapping(target = "fields", expression = "java(getRegionsIds(farmer.getRegions()))")
  FarmerFullDTO toFullDTO(Farmer farmer);

  default Collection<Long> getRegionsIds(Collection<Region> regions) {
    return regions.stream().map(region -> region.getId()).collect(Collectors.toList());
  }
}
