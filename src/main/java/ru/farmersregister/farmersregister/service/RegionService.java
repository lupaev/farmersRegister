package ru.farmersregister.farmersregister.service;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.SortRegion;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.exception.ElemNotFound;

/**
 * Сервис для сущности районов
 */
public interface RegionService {


  Collection<RegionDTO> findAll();


  RegionDTO addRegion(RegionDTO regionDTO);


  RegionDTO patchRegion(RegionDTO regionDTO) throws ElemNotFound;

}
