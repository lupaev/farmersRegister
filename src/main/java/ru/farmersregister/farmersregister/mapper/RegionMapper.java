package ru.farmersregister.farmersregister.mapper;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;

public interface RegionMapper {

  Region toEntity(RegionDTO regionDTO);

  RegionDTO toDTO(Region region);

  Collection<RegionDTO> toDTOList(Collection<RegionDTO> list);

}
