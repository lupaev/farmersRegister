package ru.farmersregister.farmersregister.service;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;

public interface FarmerService {

  Collection<FarmerDTO> findAll();
  FarmerDTO addFarmer(FarmerDTO farmerDTO);

}
