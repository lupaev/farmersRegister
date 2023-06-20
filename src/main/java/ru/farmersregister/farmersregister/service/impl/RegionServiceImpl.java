package ru.farmersregister.farmersregister.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.mapper.RegionMapper;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

  private final RegionRepository regionRepository;
  private final RegionMapper regionMapper;

  public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper) {
    this.regionRepository = regionRepository;
    this.regionMapper = regionMapper;
  }

  @Override
  public Collection<RegionDTO> findAll() {
    Collection<Region> collection = regionRepository.findAll(Sort.by(Direction.DESC, "codeRegion"));
    return new ArrayList<>(regionMapper.toDTOList(collection));
  }

  @Override
  public Collection<RegionDTO> findAllByName() {
    Collection<Region> collection = regionRepository.findAll();
    return new ArrayList<>(regionMapper.toDTOList(collection));
  }




}
