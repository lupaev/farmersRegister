package ru.farmersregister.farmersregister.service.impl;

import static ru.farmersregister.farmersregister.entity.Status.ACTIVE;
import static ru.farmersregister.farmersregister.entity.Status.NONACTIVE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.exception.ElemNotFound;
import ru.farmersregister.farmersregister.loger.FormLogInfo;
import ru.farmersregister.farmersregister.mapper.FarmerFullMapper;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

@Service
@Slf4j
public class FarmerServiceImpl implements FarmerService {

  @Autowired
  private FarmerRepository farmerRepository;

  @Autowired
  private FarmerMapper farmerMapper;

  @Autowired
  private FarmerFullMapper farmerFullMapper;

  public Collection<FarmerDTO> findAll(SortFarmer sortFarmer) {
    log.info(FormLogInfo.getInfo());
    Collection<FarmerDTO> collection = farmerMapper.toDTOList(farmerRepository.findAll());
    Comparator<FarmerDTO> comparatorByName = Comparator.comparing(FarmerDTO::getName);
    Comparator<FarmerDTO> comparatorByInn = Comparator.comparing(FarmerDTO::getInn);
    Comparator<FarmerDTO> comparatorByRegistration = Comparator.comparing(
        FarmerDTO::getRegistrationRegion);
    Comparator<FarmerDTO> comparatorByDate = Comparator.comparing(FarmerDTO::getDateRegistration);

    switch (sortFarmer.name()) {
      case ("NAME"):
        return collection.stream().sorted(comparatorByName)
            .filter(farmerDTO -> farmerDTO.getStatus().equals(ACTIVE)).collect(Collectors.toList());
      case ("INN"):
        return collection.stream().sorted(comparatorByInn)
            .filter(farmerDTO -> farmerDTO.getStatus().equals(ACTIVE)).collect(Collectors.toList());
      case ("REGISTRATION"):
        return collection.stream().sorted(comparatorByRegistration)
            .filter(farmerDTO -> farmerDTO.getStatus().equals(ACTIVE)).collect(Collectors.toList());
      case ("DATE"):
        return collection.stream().sorted(comparatorByDate)
            .filter(farmerDTO -> farmerDTO.getStatus().equals(ACTIVE)).collect(Collectors.toList());
      case ("ACTIVE"):
        return collection.stream().filter(farmerDTO -> farmerDTO.getStatus().equals(ACTIVE))
            .collect(Collectors.toList());
      case ("NONACTIVE"):
        return collection.stream().filter(farmerDTO -> farmerDTO.getStatus().equals(NONACTIVE))
            .collect(Collectors.toList());
      default:
        return new ArrayList<>(collection);
    }
  }

  @Override
  public FarmerDTO addFarmer(String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Long registrationRegion,
      Long regionId) {
    log.info(FormLogInfo.getInfo());
    FarmerDTO farmerDTO = getFarmerDTO(name, legalForm, inn, kpp, ogrn, dateRegistration, status,
        registrationRegion);
    farmerRepository.save(farmerMapper.toEntity(farmerDTO));
    Farmer farmer = farmerRepository.findByInnAndName(inn, name);
    farmerRepository.saveFarmerFieldInOtherRegions(farmer.getId(), regionId);
    return farmerDTO;
  }

  @Override
  public FarmerDTO patchFarmer(Long id, String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Long registrationRegion) {
    log.info(FormLogInfo.getInfo());
    FarmerDTO farmerDTO = getFarmerDTO(name, legalForm, inn, kpp, ogrn, dateRegistration, status,
        registrationRegion);
    Farmer farmer = farmerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Farmer not found on :: " + id));
    farmerMapper.updateEntity(farmerDTO, farmer);
    farmerRepository.save(farmer);
    return farmerMapper.toDTO(farmer);
  }

  @Override
  public FarmerFullDTO getFarmer(Long id) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Farmer not found on :: " + id));
    return farmerFullMapper.toFullDTO(farmer);
  }

  private FarmerDTO getFarmerDTO(String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Long registrationRegion) {
    log.info(FormLogInfo.getInfo());
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
