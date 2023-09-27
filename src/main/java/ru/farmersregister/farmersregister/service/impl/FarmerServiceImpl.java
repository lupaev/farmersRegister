package ru.farmersregister.farmersregister.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.exception.ElementNotFound;
import ru.farmersregister.farmersregister.loger.FormLogInfo;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

import java.sql.SQLException;
import java.util.Collection;

@Service
@Slf4j
public class FarmerServiceImpl implements FarmerService {

  private final FarmerRepository farmerRepository;

  private final FarmerMapper farmerMapper;


  public FarmerServiceImpl(FarmerRepository farmerRepository, FarmerMapper farmerMapper) {
    this.farmerRepository = farmerRepository;
    this.farmerMapper = farmerMapper;
  }

  public Page<FarmerDTO> findAll(Predicate predicate, Pageable pageable) {
    Page<Farmer> entities = farmerRepository.findAll(predicate, pageable);
    return entities.map(farmerMapper::toDTO);
  }

  @Override
  public Collection<FarmerDTO> findAllInArchive() {
    Collection<Farmer> entities = farmerRepository.findAllInArchive();
    return farmerMapper.toDTOList(entities);
  }

  @Override
  public FarmerDTO addFarmer(CreateFarmerDTO createFarmerDTO) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerRepository.save(farmerMapper.createEntity(createFarmerDTO));
    return farmerMapper.toDTO(farmer);
  }


  @Override
  public FarmerDTO getFarmer(Long id) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerRepository.findById(id)
        .orElseThrow(() -> new ElementNotFound("Farmer not found on :: " + id));
    return farmerMapper.toDTO(farmer);
  }

  @Override
  public FarmerDTO patchFarmer(Long id, CreateFarmerDTO createFarmerDTO) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerRepository.findById(id)
        .orElseThrow(() -> new ElementNotFound("Farmer not found on :: " + id));
    farmerMapper.updateEntity(createFarmerDTO, farmer);
    farmerRepository.save(farmer);
    return farmerMapper.toDTO(farmerRepository.findById(id)
        .orElseThrow(() -> new ElementNotFound("Farmer not found on :: " + id)));
  }

  @Override
  public FarmerDTO delFarmer(Long id) throws SQLException {
    Farmer farmer = farmerRepository.findById(id)
        .orElseThrow(() -> new ElementNotFound("Region not found on :: " + id));
    farmerRepository.saveToArchive(id);
    farmerRepository.deleteById(id);
    return farmerMapper.toDTO(farmer);
  }


}
