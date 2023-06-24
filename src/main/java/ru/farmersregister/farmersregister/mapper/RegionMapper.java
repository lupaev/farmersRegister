package ru.farmersregister.farmersregister.mapper;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;

/**
 * Маппер для района
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RegionMapper {

  /**
   * Преобразование DTO в сущность района
   * @param regionDTO
   * @return
   */
  Region toEntity(RegionDTO regionDTO);

  /**
   * Преобразование сущности района в DTO
   * @param region
   * @return
   */
  RegionDTO toDTO(Region region);

  /**
   * Преобразование коллекции районов в коллекцию  DTO
   * @param list
   * @return
   */
  Collection<RegionDTO> toDTOList(Collection<Region> list);

  /**
   * Обновление сущности района из данных DTO
   * @param regionDTO
   * @param region
   */
  void updateEntity(RegionDTO regionDTO, @MappingTarget Region region);

}
