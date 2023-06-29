package ru.farmersregister.farmersregister.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;

import java.util.Collection;

/**
 * Маппер для фермера
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FarmerMapper {

  /**
   * Преобразование DTO в сущность фермера
   * @param farmerDTO
   * @return
   */
  @Mapping(source = "registrationRegion", target = "region.id")
  Farmer toEntity(FarmerDTO farmerDTO);

  /**
   * Преобразование сущности фермера в DTO
   * @param farmer
   * @return
   */
  @Mapping(source = "region.id", target = "registrationRegion")
  FarmerDTO toDTO(Farmer farmer);

  /**
   * Преобразование коллекции фермеров в коллекцию  DTO
   * @param list
   * @return
   */
  Collection<FarmerDTO> toDTOList(Collection<Farmer> list);

  /**
   * Обновление сущности фермера из данных DTO
   * @param farmerDTO
   * @param farmer
   */
  void updateEntity(FarmerDTO farmerDTO, @MappingTarget Farmer farmer);


}
