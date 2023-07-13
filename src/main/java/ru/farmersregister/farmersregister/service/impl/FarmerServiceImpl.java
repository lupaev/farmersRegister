package ru.farmersregister.farmersregister.service.impl;

import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.exception.ElemNotFound;
import ru.farmersregister.farmersregister.loger.FormLogInfo;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

import java.util.ArrayList;
import java.util.Collection;
import ru.farmersregister.farmersregister.specification.SpecificationDTO;

@Service
@Slf4j
public class FarmerServiceImpl implements FarmerService {

  private final FarmerRepository farmerRepository;

  private final FarmerMapper farmerMapper;

  private final SpecificationDTO specificationDTO;


  public FarmerServiceImpl(FarmerRepository farmerRepository, FarmerMapper farmerMapper,
      SpecificationDTO specificationDTO) {
    this.farmerRepository = farmerRepository;
    this.farmerMapper = farmerMapper;
    this.specificationDTO = specificationDTO;
  }

  public Page<FarmerDTO> findAll(RequestDTO requestDTO, Pageable pageable) {
    Specification<Farmer> searchSpecification = specificationDTO.getSearchSpecification(
        requestDTO.getSearchRequestDTO(), requestDTO.getGlobalOperator());
    Page<Farmer> entities = farmerRepository.findAll(searchSpecification, pageable);
    return entities.map(farmerMapper::toDTO);

  }

  @Override
  public Page<FarmerDTO> findAllInArchive(Pageable pageable) {
    Page<Farmer> entities = farmerRepository.findAllInArchive(pageable);
    return entities.map(farmerMapper::toDTO);
  }

  @Override
  public FarmerDTO addFarmer(FarmerDTO farmerDTO) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerMapper.toEntity(farmerDTO);
    farmerRepository.save(farmer);
    return farmerMapper.toDTO(farmerRepository.findByInnAndName(farmerDTO.getInn(),
        farmerDTO.getName()));
  }


  @Override
  public FarmerDTO getFarmer(Long id) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Farmer not found on :: " + id));
    return farmerMapper.toDTO(farmer);  }

  @Override
  public FarmerDTO patchFarmer(Long id, FarmerDTO farmerDTO) {
    log.info(FormLogInfo.getInfo());
    Farmer farmer = farmerRepository.findById(id)
            .orElseThrow(() -> new ElemNotFound("Farmer not found on :: " + id));
    farmerMapper.updateEntity(farmerDTO, farmer);
    farmerRepository.save(farmer);
    return farmerMapper.toDTO(farmerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Farmer not found on :: " + id)));
  }

  @Override
  public FarmerDTO delFarmer(Long id) throws SQLException {
    Farmer farmer = farmerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Region not found on :: " + id));
    try {
      farmerRepository.saveToArchive(id);
      farmerRepository.deleteById(id);
    } catch (Exception exception) {
      throw new SQLException("В данном регионе есть зарегистрированные фермеры");
    }
    return farmerMapper.toDTO(farmer);
  }


}
