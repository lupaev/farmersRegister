package ru.farmersregister.farmersregister.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.exception.ElemNotFound;
import ru.farmersregister.farmersregister.loger.FormLogInfo;
import ru.farmersregister.farmersregister.mapper.FarmerFullMapper;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import static ru.farmersregister.farmersregister.entity.Status.ACTIVE;
import static ru.farmersregister.farmersregister.entity.Status.NONACTIVE;

@Service
@Slf4j
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

  public Collection<FarmerDTO> findAll() {
    return new ArrayList<>(farmerMapper.toDTOList(farmerRepository.findAll()));

  }

  @Override
  public FarmerDTO addFarmer(FarmerDTO farmerDTO) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerMapper.toEntity(farmerDTO);
    farmerRepository.save(farmer);
    return farmerMapper.toDTO(farmer);
  }


  @Override
  public FarmerDTO getFarmer(Long id) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Farmer not found on :: " + id));
    return farmerMapper.toDTO(farmer);  }




}
