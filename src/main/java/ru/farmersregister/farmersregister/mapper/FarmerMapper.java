package ru.farmersregister.farmersregister.mapper;

import org.mapstruct.*;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.repository.RegionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Маппер для фермера
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = RegionRepository.class)
public interface FarmerMapper {


    /**
     * Преобразование DTO в сущность фермера
     *
     * @param farmerDTO
     * @return
     */
    @Mapping(source = "registrationRegion", target = "region.id")
    @Mapping(source = "regionIds", target = "fields", qualifiedByName = "idToRegion")
    Farmer toEntity(FarmerDTO farmerDTO);

    @Named("idToRegion")
    static List<Region> idToRegion(List<Long> ids) {
        if ((ids == null) || ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Region> regionList = new ArrayList<>(ids.size());
        for (Long id:ids) {
            Region region = new Region();
            region.setId(id);
            regionList.add(region);
        }
//        return ids.stream().map(() -> new Region().setId(ids.get())).collect(Collectors.toList());
        return regionList;

    }


    /**
     * Преобразование сущности фермера в DTO
     *
     * @param farmer
     * @return
     */
    @Mapping(source = "region.id", target = "registrationRegion")
    @Mapping(source = "fields", target = "regionIds", qualifiedByName = "RegionToId")
    FarmerDTO toDTO(Farmer farmer);

    @Named("RegionToId")
    static List<Long> RegionToId(List<Region> regions) {
        List<Long> ids = new ArrayList<>(regions.size());
        for (Region region : regions) {
            ids.add(region.getId());
        }
        return ids;
    }

    /**
     * Преобразование коллекции фермеров в коллекцию  DTO
     *
     * @param list
     * @return
     */
    Collection<FarmerDTO> toDTOList(Collection<Farmer> list);

    /**
     * Обновление сущности фермера из данных DTO
     *
     * @param farmerDTO
     * @param farmer
     */
    void updateEntity(FarmerDTO farmerDTO, @MappingTarget Farmer farmer);


}
