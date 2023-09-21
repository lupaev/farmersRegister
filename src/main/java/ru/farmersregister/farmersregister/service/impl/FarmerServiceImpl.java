package ru.farmersregister.farmersregister.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.exception.ElementNotFound;
import ru.farmersregister.farmersregister.loger.FormLogInfo;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;
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
    public FarmerDTO addFarmer(CreateFarmerDTO farmerDTO) {
        log.info(FormLogInfo.getInfo());
        Farmer farmer = farmerMapper.createEntity(farmerDTO);
        return farmerMapper.toDTO(farmerRepository.save(farmer));
    }


    @Override
    public FarmerDTO getFarmer(Long id) {
        log.info(FormLogInfo.getInfo());
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new ElementNotFound("Farmer not found on :: " + id));
        return farmerMapper.toDTO(farmer);
    }

    @Override
    public FarmerDTO patchFarmer(Long id, CreateFarmerDTO farmerDTO) {
        log.info(FormLogInfo.getInfo());
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new ElementNotFound("Farmer not found on :: " + id));
        farmerMapper.updateEntity(farmerDTO, farmer);
        return farmerMapper.toDTO(farmerRepository.save(farmer));
    }

    @Override
    public FarmerDTO delFarmer(Long id) {
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new ElementNotFound("Region not found on :: " + id));
        farmerRepository.saveToArchive(id);
        farmerRepository.saveFarmerFieldsToArchive(id);
        farmerRepository.deleteById(id);
        return farmerMapper.toDTO(farmer);
    }


}
