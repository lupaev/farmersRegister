package ru.farmersregister.farmersregister.service;

import java.time.LocalDate;
import java.util.Collection;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.entity.Status;

public interface FarmerService {

  Collection<FarmerDTO> findAll(SortFarmer sortFarmer);

  FarmerDTO addFarmer(String name, LegalForm legalForm, Integer inn, Integer kpp, Integer ogrn,
      LocalDate dateRegistration, Status status, Long registrationRegion, Long regionId);

  FarmerDTO patchFarmer(Long id, String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Long registrationRegion)
      throws Exception;

  FarmerFullDTO getFarmer(Long id);

}
