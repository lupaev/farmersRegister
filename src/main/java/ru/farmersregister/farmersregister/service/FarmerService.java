package ru.farmersregister.farmersregister.service;

import ru.farmersregister.farmersregister.dto.FarmerDTO;

import java.util.Collection;

/**
 * Сервис для сущности фермеров
 */
public interface FarmerService {


  Collection<FarmerDTO> findAll();


  FarmerDTO addFarmer(FarmerDTO farmerDTO);



  FarmerDTO getFarmer(Long id);

}
