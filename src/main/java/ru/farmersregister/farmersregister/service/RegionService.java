package ru.farmersregister.farmersregister.service;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;

public interface RegionService {
  Collection<RegionDTO>findAll();

  Collection<RegionDTO> findAllByName();
}
