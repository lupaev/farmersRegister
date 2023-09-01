package ru.farmersregister.farmersregister.mapper;

import org.springframework.stereotype.Component;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.repository.RegionRepository;

/**
 * Вспомогательный класс для мапинга регионов
 */
@Component
public class RegionToLong {
    private final RegionRepository regionRepository;

    public RegionToLong(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Long entityToLong(Region region){
        return region.getId();
    }

    public Region longToEntity(Long id) {
        return regionRepository.findById(id).get();
    }
}
