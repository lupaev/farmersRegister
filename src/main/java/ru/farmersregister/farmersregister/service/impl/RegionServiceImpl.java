package ru.farmersregister.farmersregister.service.impl;

import static ru.farmersregister.farmersregister.entity.Status.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

  @Autowired
  private RegionRepository regionRepository;
  @Autowired
  private RegionMapper regionMapper;

  @Override
  public Collection<RegionDTO> findAll(SortRegion sortRegion) {
    log.info(FormLogInfo.getInfo());
    Collection<RegionDTO> collection = regionMapper.toDTOList(regionRepository.findAll());
    Comparator<RegionDTO> comparatorByName = Comparator.comparing(RegionDTO::getName);
    Comparator<RegionDTO> comparatorByCode = Comparator.comparing(RegionDTO::getCodeRegion);
    switch (sortRegion.name()) {
      case ("NAME"):
        return collection.stream().sorted(comparatorByName)
            .filter(regionDTO -> regionDTO.getStatus().equals(ACTIVE)).collect(Collectors.toList());
      case ("CODE"):
        return collection.stream().sorted(comparatorByCode)
            .filter(regionDTO -> regionDTO.getStatus().equals(ACTIVE)).collect(Collectors.toList());
      case ("NONACTIVE"):
        return collection.stream().sorted(comparatorByCode)
            .filter(regionDTO -> regionDTO.getStatus().equals(NONACTIVE)).collect(
                Collectors.toList());
      default:
        return new ArrayList<>(collection);
    }
  }

  @Override
  public RegionDTO addRegion(String name, Integer codeRegion, Status status) {
    log.info(FormLogInfo.getInfo());
    RegionDTO regionDTO = getRegionDTO(name, codeRegion, status);
    regionRepository.save(regionMapper.toEntity(regionDTO));
    return regionDTO;
  }

  @Override
  public RegionDTO patchRegion(Long id, String name, Integer codeRegion, Status status)
      throws ElemNotFound {
    log.info(FormLogInfo.getInfo());
    RegionDTO regionDTO = getRegionDTO(name, codeRegion, status);
    Region region = regionRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Region not found on :: " + id));
    regionMapper.updateEntity(regionDTO, region);
    regionRepository.save(region);
    return regionMapper.toDTO(region);
  }

  private RegionDTO getRegionDTO(String name, Integer codeRegion, Status status) {
    log.info(FormLogInfo.getInfo());
    RegionDTO regionDTO = new RegionDTO();
    regionDTO.setName(name);
    regionDTO.setCodeRegion(codeRegion);
    regionDTO.setStatus(status);
    return regionDTO;
  }


}
