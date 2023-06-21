package ru.farmersregister.farmersregister.service.impl;

import static ru.farmersregister.farmersregister.entity.Status.ACTIVE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService {

  private final FarmerRepository farmerRepository;
  private final FarmerMapper farmerMapper;

  public FarmerServiceImpl(FarmerRepository farmerRepository, FarmerMapper farmerMapper) {
    this.farmerRepository = farmerRepository;
    this.farmerMapper = farmerMapper;
  }


  public Collection<FarmerDTO> findAll(SortFarmer sortFarmer) {

    switch (sortFarmer.name()) {
      case ("NAME"):
        Collection<Farmer> collection = farmerRepository.findAll();
        return new ArrayList<>(farmerMapper.toDTOList(collection.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("INN"):
        Collection<Farmer> collection1 = farmerRepository.findAll();
        return new ArrayList<>(farmerMapper.toDTOList(collection1.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("REGISTRATION"):
        Collection<Farmer> collection2 = farmerRepository.findAll();
        return new ArrayList<>(farmerMapper.toDTOList(collection2.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("DATE"):
        Collection<Farmer> collection3 = farmerRepository.findAll();
        return new ArrayList<>(farmerMapper.toDTOList(collection3.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("STATUS"):
        Collection<Farmer> collection4 = farmerRepository.findAll();
        return new ArrayList<>(farmerMapper.toDTOList(collection4.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      default:

    }

    Collection<Farmer> collection = farmerRepository.findAll();
    return new ArrayList<>(farmerMapper.toDTOList(collection.stream()
        .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
  }

  @Override
  public FarmerDTO addFarmer(String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Integer registrationRegion) {
    FarmerDTO farmerDTO = new FarmerDTO();
    farmerDTO.setName(name);
    farmerDTO.setLegalForm(legalForm);
    farmerDTO.setInn(inn);
    farmerDTO.setKpp(kpp);
    farmerDTO.setOgrn(ogrn);
    farmerDTO.setDateRegistration(dateRegistration);
    farmerDTO.setStatus(status);
    farmerDTO.setRegistrationRegion(registrationRegion);
    farmerRepository.save(farmerMapper.toEntity(farmerDTO));
    return farmerDTO;
  }

  @Override
  public FarmerDTO patchFarmer(Long id, String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Integer registrationRegion)
      throws Exception {
    FarmerDTO farmerDTO = new FarmerDTO();
    farmerDTO.setId(id);
    farmerDTO.setName(name);
    farmerDTO.setLegalForm(legalForm);
    farmerDTO.setInn(inn);
    farmerDTO.setKpp(kpp);
    farmerDTO.setOgrn(ogrn);
    farmerDTO.setDateRegistration(dateRegistration);
    farmerDTO.setStatus(status);
    farmerDTO.setRegistrationRegion(registrationRegion);
    Farmer farmer = farmerRepository.findById(id).orElseThrow(Exception::new);
    farmerMapper.updateEntity(farmerDTO, farmer);
    farmerRepository.save(farmer);
    return farmerMapper.toDTO(farmer);
  }


}
