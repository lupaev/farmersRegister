package ru.farmersregister.farmersregister.service.impl;

import static ru.farmersregister.farmersregister.entity.Status.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.SortRegion;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.exception.ElemNotFound;
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
  public Collection<RegionDTO> findAll() {
    return new ArrayList<>(regionMapper.toDTOList(regionRepository.findAll()));

  }

  @Override
  public RegionDTO addRegion(RegionDTO regionDTO) {
    log.info(FormLogInfo.getInfo());
    regionRepository.save(regionMapper.toEntity(regionDTO));
    return regionDTO;
  }

  @Override
  public RegionDTO patchRegion(RegionDTO regionDTO)
      throws ElemNotFound {
    log.info(FormLogInfo.getInfo());
    Region region = regionRepository.findById(regionDTO.getId())
        .orElseThrow(() -> new ElemNotFound("Region not found on :: " + regionDTO.getId()));
    regionMapper.updateEntity(regionDTO, region);
    regionRepository.save(region);
    return regionMapper.toDTO(region);
  }




}
