package ru.farmersregister.farmersregister.service.impl;

import static ru.farmersregister.farmersregister.entity.Status.ACTIVE;
import static ru.farmersregister.farmersregister.entity.Status.NONACTIVE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.mapper.FarmerFullMapper;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService {

  private final FarmerRepository farmerRepository;
  private final FarmerMapper farmerMapper;

  private final FarmerFullMapper farmerFullMapper;

  public FarmerServiceImpl(FarmerRepository farmerRepository, FarmerMapper farmerMapper,
      FarmerFullMapper farmerFullMapper) {
    this.farmerRepository = farmerRepository;
    this.farmerMapper = farmerMapper;
    this.farmerFullMapper = farmerFullMapper;
  }


  public Collection<FarmerDTO> findAll(SortFarmer sortFarmer) {

    switch (sortFarmer.name()) {
      case ("NAME"):
        Collection<Farmer> collection = farmerRepository.findAll(Sort.by(Direction.ASC, "name"));
        return new ArrayList<>(farmerMapper.toDTOList(collection.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("INN"):
        Collection<Farmer> collection1 = farmerRepository.findAll(Sort.by(Direction.ASC, "inn"));
        return new ArrayList<>(farmerMapper.toDTOList(collection1.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("REGISTRATION"):
        Collection<Farmer> collection2 = farmerRepository.findAll(
            Sort.by(Direction.ASC, "registrationRegion"));
        return new ArrayList<>(farmerMapper.toDTOList(collection2.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("DATE"):
        Collection<Farmer> collection3 = farmerRepository.findAll(
            Sort.by(Direction.ASC, "dateRegistration"));
        return new ArrayList<>(farmerMapper.toDTOList(collection3.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("ACTIVE"):
        Collection<Farmer> collection4 = farmerRepository.findAll();
        return new ArrayList<>(farmerMapper.toDTOList(collection4.stream()
            .filter(farmer -> farmer.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
      case ("NONACTIVE"):
        Collection<Farmer> collection5 = farmerRepository.findAll();
        return new ArrayList<>(farmerMapper.toDTOList(collection5.stream()
            .filter(farmer -> farmer.getStatus().equals(NONACTIVE)).collect(Collectors.toList())));
      default:
        return farmerMapper.toDTOList(farmerRepository.findAll());
    }
  }

  @Override
  public FarmerDTO addFarmer(String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Integer registrationRegion, Long regionId) {
    FarmerDTO farmerDTO = getFarmerDTO(name, legalForm, inn, kpp, ogrn, dateRegistration, status,
        registrationRegion);
    farmerRepository.save(farmerMapper.toEntity(farmerDTO));
    Farmer farmer = farmerRepository.findByInnAndName(inn, name);
    farmerRepository.saveFarmerFieldInOtherRegions(farmer.getId(), regionId);
    return farmerDTO;
  }

  @Override
  public FarmerDTO patchFarmer(Long id, String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Integer registrationRegion)
      throws Exception {
    FarmerDTO farmerDTO = getFarmerDTO(name, legalForm, inn, kpp, ogrn, dateRegistration, status,
        registrationRegion);
    Farmer farmer = farmerRepository.findById(id).orElseThrow(Exception::new);
    farmerMapper.updateEntity(farmerDTO, farmer);
    farmerRepository.save(farmer);
    return farmerMapper.toDTO(farmer);
  }

  @Override
  public FarmerFullDTO getFarmer(Long id) {
    Farmer farmer = farmerRepository.findById(id).get();
    return farmerFullMapper.toFullDTO(farmer);
  }

  private FarmerDTO getFarmerDTO(String name, LegalForm legalForm, Integer inn, Integer kpp,
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
    return farmerDTO;
  }


}
