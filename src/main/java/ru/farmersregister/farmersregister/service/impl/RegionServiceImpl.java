package ru.farmersregister.farmersregister.service.impl;

import static ru.farmersregister.farmersregister.entity.Status.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.SortRegion;
import ru.farmersregister.farmersregister.entity.Status;
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
  public Collection<RegionDTO> findAll(SortRegion sortRegion) {
    log.info(FormLogInfo.getInfo());
    if (sortRegion.name().equals("NAME")) {
      Collection<Region> collection = regionRepository.findAll(Sort.by(Direction.ASC, "name"));
      return new ArrayList<>(regionMapper.toDTOList(collection.stream()
          .filter(region -> region.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
    } else if (sortRegion.name().equals("CODE")) {
      Collection<Region> collection = regionRepository.findAll(
          Sort.by(Direction.ASC, "codeRegion"));
      return new ArrayList<>(regionMapper.toDTOList(collection.stream()
          .filter(region -> region.getStatus().equals(ACTIVE)).collect(Collectors.toList())));
    } else {
      Collection<Region> collection = regionRepository.findAll();
      return new ArrayList<>(regionMapper.toDTOList(collection.stream()
          .filter(region -> region.getStatus().equals(NONACTIVE)).collect(Collectors.toList())));
    }

  }

  @Override
  public RegionDTO addRegion(String name, Integer codeRegion, Status status) {
    log.info(FormLogInfo.getInfo());
    RegionDTO regionDTO = new RegionDTO();
    regionDTO.setName(name);
    regionDTO.setCodeRegion(codeRegion);
    regionDTO.setStatus(status);
    regionRepository.save(regionMapper.toEntity(regionDTO));
    return regionDTO;
  }

  @Override
  public RegionDTO patchRegion(Long id, String name, Integer codeRegion, Status status)
      throws Exception {
    log.info(FormLogInfo.getInfo());
    RegionDTO regionDTO = new RegionDTO();
    regionDTO.setId(id);
    regionDTO.setName(name);
    regionDTO.setCodeRegion(codeRegion);
    regionDTO.setStatus(status);
    Region region = regionRepository.findById(id).orElseThrow(Exception::new);
    regionMapper.updateEntity(regionDTO, region);
    regionRepository.save(region);
    return regionMapper.toDTO(region);
  }


}
