package ru.farmersregister.farmersregister.service.impl;

import com.querydsl.core.types.Predicate;
import java.sql.SQLException;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.exception.ElemNotFound;
import ru.farmersregister.farmersregister.exception.MoveToAchive;
import ru.farmersregister.farmersregister.loger.FormLogInfo;
import ru.farmersregister.farmersregister.mapper.RegionMapper;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.RegionService;

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
  public RegionDTO addRegion(RegionDTO regionDTO) {
    log.info(FormLogInfo.getInfo());
    Region region = regionRepository.save(regionMapper.toEntity(regionDTO));
    return regionMapper.toDTO(region);
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
