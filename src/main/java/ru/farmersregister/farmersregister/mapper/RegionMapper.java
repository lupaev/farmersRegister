package ru.farmersregister.farmersregister.mapper;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RegionMapper {

  Region toEntity(RegionDTO regionDTO);

  RegionDTO toDTO(Region region);

  Collection<RegionDTO> toDTOList(Collection<RegionDTO> list);

}
