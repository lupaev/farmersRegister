package ru.farmersregister.farmersregister.mapper;

import org.mapstruct.Mapper;

/**
 * Маппер для фермера
 */
@Mapper
public interface FarmerFullMapper {
//
//  /**
//   * Маппинг сущности фермера в DTO
//   * @param farmer
//   * @return
//   */
//  @Mapping(target = "id", source = "id")
//  @Mapping(target = "name", source = "name")
//  @Mapping(target = "legalForm", source = "legalForm")
//  @Mapping(target = "inn", source = "inn")
//  @Mapping(target = "kpp", source = "kpp")
//  @Mapping(target = "ogrn", source = "ogrn")
//  @Mapping(target = "registrationRegionName", source = "region.name")
//  @Mapping(target = "dateRegistration", source = "dateRegistration")
//  @Mapping(target = "status", source = "status")
//  @Mapping(target = "fields", expression = "java(getRegionsIds(farmer.getRegions()))")
//  FarmerFullDTO toFullDTO(Farmer farmer);
//
//  /**
//   * Вспомогательный метод получения наименования района регистрации
//   * @param regions
//   * @return
//   */
//  default Collection<Long> getRegionsIds(Collection<Region> regions) {
//    return regions.stream().map(region -> region.getId()).collect(Collectors.toList());
//  }
}
