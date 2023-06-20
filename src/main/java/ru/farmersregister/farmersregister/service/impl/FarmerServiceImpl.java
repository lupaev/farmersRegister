package ru.farmersregister.farmersregister.service.impl;

import static ru.farmersregister.farmersregister.entity.Status.active;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService {

  private final FarmerRepository farmerRepository;
  private final FarmerMapper farmerMapper;

  public FarmerServiceImpl(FarmerRepository farmerRepository, FarmerMapper farmerMapper) {
    this.farmerRepository = farmerRepository;
    this.farmerMapper = farmerMapper;
  }


  public Collection<FarmerDTO> findAll() {
    Collection<Farmer> collection = farmerRepository.findAll();
    return new ArrayList<>(farmerMapper.toDTOList(collection.stream()
        .filter(farmer -> farmer.getStatus().equals(active)).collect(Collectors.toList())));
  }

  @Override
  public FarmerDTO addFarmer(FarmerDTO farmerDTO) {
    farmerRepository.save(farmerMapper.toEntity(farmerDTO));
    return farmerDTO;
  }
}
