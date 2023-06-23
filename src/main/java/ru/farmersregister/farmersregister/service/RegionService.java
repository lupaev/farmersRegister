package ru.farmersregister.farmersregister.service;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.SortRegion;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.exception.ElemNotFound;

public interface RegionService {

  Collection<RegionDTO> findAll(SortRegion sortRegion);

  RegionDTO addRegion(String name, Integer codeRegion, Status status);

  RegionDTO patchRegion(Long id, String name, Integer codeRegion, Status status) throws ElemNotFound;

}
