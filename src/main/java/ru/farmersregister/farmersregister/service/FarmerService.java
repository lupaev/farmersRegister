package ru.farmersregister.farmersregister.service;

import java.time.LocalDate;
import java.util.Collection;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.Status;

public interface FarmerService {

  Collection<FarmerDTO> findAll();
  FarmerDTO addFarmer(String name, LegalForm legalForm, Integer inn, Integer kpp, Integer ogrn,
      LocalDate dateRegistration, Status status, Integer registrationRegion);

  FarmerDTO patchFarmer(Long id, String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Integer registrationRegion)
      throws Exception;

}
