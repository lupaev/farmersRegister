package ru.farmersregister.farmersregister.service;

import java.sql.SQLException;
import ru.farmersregister.farmersregister.dto.FarmerDTO;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;

/**
 * Сервис для сущности фермеров
 */
public interface FarmerService {


  Collection<FarmerDTO> findAll();


  FarmerDTO addFarmer(FarmerDTO farmerDTO);



  FarmerDTO getFarmer(Long id);

  FarmerDTO patchFarmer(Long id, FarmerDTO farmerDTO);

  FarmerDTO delFarmer(Long id) throws SQLException;
}
