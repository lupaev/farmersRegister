package ru.farmersregister.farmersregister.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.farmersregister.farmersregister.dto.CreateRegionDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;

import java.util.Collection;

/**
 * Маппер для района
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RegionMapper {

  /**
   * Преобразование DTO в сущность района
   *
   * @param regionDTO
   * @return
   */
  Region toEntity(RegionDTO regionDTO);

  /**
   * ДТО для создания сущности региона
   *
   * @param createRegionDTO
   * @return
   */
  @Mapping(ignore = true, target = "id")
  Region createEntity(CreateRegionDTO createRegionDTO);

  /**
   * Преобразование сущности района в DTO
   *
   * @param region
   * @return
   */
  RegionDTO toDTO(Region region);

  /**
   * Преобразование коллекции районов в коллекцию  DTO
   *
   * @param list
   * @return
   */
  Collection<RegionDTO> toDTOList(Collection<Region> list);

  /**
   * Обновление сущности района из данных DTO
   *
   * @param regionDTO
   * @param region
   */
  void updateEntity(CreateRegionDTO regionDTO, @MappingTarget Region region);

}
