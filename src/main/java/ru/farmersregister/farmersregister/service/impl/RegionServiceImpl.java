package ru.farmersregister.farmersregister.service.impl;

import static ru.farmersregister.farmersregister.entity.Status.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.Status;
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
    return new ArrayList<>(regionMapper.toDTOList(collection.stream()
        .filter(region -> region.getStatus().equals(active)).collect(Collectors.toList())));
  }

//  @Override
//  public Collection<RegionDTO> findAllByName() {
//    Collection<Region> collection = regionRepository.findAll();
//    return new ArrayList<>(regionMapper.toDTOList(collection));
//  }


  @Override
  public RegionDTO addRegion(RegionDTO regionDTO) {
    regionRepository.save(regionMapper.toEntity(regionDTO));
    return regionDTO;
  }

  @Override
  public RegionDTO patchRegion(long id, String name, Integer codeRegion, Status status) throws Exception {
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
