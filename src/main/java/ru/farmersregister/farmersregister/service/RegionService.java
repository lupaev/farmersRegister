package ru.farmersregister.farmersregister.service;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Status;

public interface RegionService {
  Collection<RegionDTO>findAll();

  RegionDTO addRegion(RegionDTO regionDTO);
  RegionDTO patchRegion(long id, String name, Integer codeRegion, Status status) throws Exception;

//  Collection<RegionDTO> findAllByName();
}
