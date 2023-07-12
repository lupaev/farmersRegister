package ru.farmersregister.farmersregister.service;

import java.sql.SQLException;
import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.exception.ElemNotFound;

/**
 * Сервис для сущности районов
 */
public interface RegionService {


  Collection<RegionDTO> findAll();


  RegionDTO addRegion(RegionDTO regionDTO);


  RegionDTO patchRegion(Long id, RegionDTO regionDTO) throws ElemNotFound;

  RegionDTO delRegion(Long id) throws SQLException;

}
