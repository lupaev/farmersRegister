package ru.farmersregister.farmersregister.service.impl;

import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.exception.ElemNotFound;
import ru.farmersregister.farmersregister.exception.MoveToAchive;
import ru.farmersregister.farmersregister.loger.FormLogInfo;
import ru.farmersregister.farmersregister.mapper.RegionMapper;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.RegionService;
import ru.farmersregister.farmersregister.specification.SpecificationDTO;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

  private final RegionRepository regionRepository;
  private final RegionMapper regionMapper;

  private final SpecificationDTO specificationDTO;

  public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper,
      SpecificationDTO specificationDTO) {
    this.regionRepository = regionRepository;
    this.regionMapper = regionMapper;
    this.specificationDTO = specificationDTO;
  }

  @Override
  public Page<RegionDTO> findAll(RequestDTO requestDTO, Pageable pageable) {
    Specification<Region> searchSpecification = specificationDTO.getSearchSpecification(
        requestDTO.getSearchRequestDTO(), requestDTO.getGlobalOperator());
    Page<Region> entities = regionRepository.findAll(searchSpecification, pageable);
    return entities.map(regionMapper::toDTO);

  }

  @Override
  public Page<RegionDTO> findAllInArchive(Pageable pageable) {
    Page<Region> entities = regionRepository.findAllInArchive(pageable);
    return entities.map(regionMapper::toDTO);

  }

  @Override
  public RegionDTO addRegion(RegionDTO regionDTO) {
    log.info(FormLogInfo.getInfo());
    regionRepository.save(regionMapper.toEntity(regionDTO));
    return regionMapper.toDTO(
        regionRepository.findByNameAndCodeRegion(regionDTO.getName(), regionDTO.getCodeRegion()));
  }

  @Override
  public RegionDTO patchRegion(Long id, RegionDTO regionDTO) throws ElemNotFound {
    log.info(FormLogInfo.getInfo());
    Region region = regionRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Region not found on :: " + id));
    regionMapper.updateEntity(regionDTO, region);
    regionRepository.save(region);
    return regionMapper.toDTO(region);
  }

  @Override
  public RegionDTO delRegion(Long id) throws SQLException {
    Region region = regionRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Region not found on :: " + id));
    try {
      regionRepository.saveToArchive(id);
      regionRepository.deleteById(id);
    } catch (Exception exception) {
      throw new MoveToAchive("В данном регионе есть зарегистрированные фермеры");
    }
    return regionMapper.toDTO(region);
  }


}
