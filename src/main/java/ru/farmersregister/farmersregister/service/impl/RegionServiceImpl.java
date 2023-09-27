package ru.farmersregister.farmersregister.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.CreateRegionDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.exception.ElementNotFound;
import ru.farmersregister.farmersregister.exception.MoveToAchiveException;
import ru.farmersregister.farmersregister.loger.FormLogInfo;
import ru.farmersregister.farmersregister.mapper.RegionMapper;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.RegionService;

import java.sql.SQLException;
import java.util.Collection;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

  private final RegionRepository regionRepository;
  private final RegionMapper regionMapper;


  public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper) {
    this.regionRepository = regionRepository;
    this.regionMapper = regionMapper;
  }

  @Override
  public Page<RegionDTO> findAll(Predicate predicate, Pageable pageable) {
    Page<Region> entities = regionRepository.findAll(predicate, pageable);
    return entities.map(regionMapper::toDTO);
  }

  @Override
  public Collection<RegionDTO> findAllInArchive() {
    return regionMapper.toDTOList(regionRepository.findAllInArchive());
  }

  @Override
  public RegionDTO addRegion(CreateRegionDTO createRegionDTO) {
    log.info(FormLogInfo.getInfo());
    Region region = regionRepository.save(regionMapper.createEntity(createRegionDTO));
    return regionMapper.toDTO(region);
  }

  @Override
  public RegionDTO patchRegion(Long id, CreateRegionDTO createRegionDTO) throws ElementNotFound {
    log.info(FormLogInfo.getInfo());
    Region region = regionRepository.findById(id)
        .orElseThrow(() -> new ElementNotFound("Region not found on :: " + id));
    regionMapper.updateEntity(createRegionDTO, region);
    regionRepository.save(region);
    return regionMapper.toDTO(region);
  }

  @Override
  public RegionDTO delRegion(Long id) throws SQLException {
    Region region = regionRepository.findById(id)
        .orElseThrow(() -> new ElementNotFound("Region not found on :: " + id));
    try {
      regionRepository.saveToArchive(id);
      regionRepository.deleteById(id);
    } catch (Exception exception) {
      throw new MoveToAchiveException("В данном регионе есть зарегистрированные фермеры");
    }
    return regionMapper.toDTO(region);
  }


}
