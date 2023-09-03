package ru.farmersregister.farmersregister.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.FarmerInArchive;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerInArchiveRepository;
import ru.farmersregister.farmersregister.service.FarmerInArchiveService;

@Service
public class FarmerInArchiveServiceImpl implements FarmerInArchiveService {

    private final FarmerInArchiveRepository farmerInArchiveRepository;
    private final FarmerMapper farmerMapper;

    public FarmerInArchiveServiceImpl(FarmerInArchiveRepository farmerInArchiveRepository, FarmerMapper farmerMapper) {
        this.farmerInArchiveRepository = farmerInArchiveRepository;
        this.farmerMapper = farmerMapper;
    }

    @Override
    public Page<FarmerDTO> findAll(Pageable pageable) {
        Page<FarmerInArchive> entities = farmerInArchiveRepository.findAll(pageable);
        return entities.map(farmerMapper::toDTO);
    }
}
