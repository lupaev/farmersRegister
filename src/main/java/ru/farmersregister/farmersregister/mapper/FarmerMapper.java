package ru.farmersregister.farmersregister.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.FarmerInArchive;

import java.util.Collection;

/**
 * Маппер для фермера
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = RegionToLong.class)
public interface FarmerMapper {


  /**
   * Преобразование DTO в сущность фермера
   *
   * @param farmerDTO
   * @return
   */
  @Mapping(source = "registrationRegion", target = "region.id")
  @Mapping(source = "regionIds", target = "fields")
  Farmer toEntity(FarmerDTO farmerDTO);


  /**
   * ДТО для создания сущности фермера
   *
   * @param createFarmerDTO
   * @return
   */
  @Mapping(source = "registrationRegion", target = "region.id")
  @Mapping(source = "regionIds", target = "fields")
  @Mapping(ignore = true, target = "id")
  Farmer createEntity(CreateFarmerDTO createFarmerDTO);


  /**
   * Преобразование сущности фермера в DTO
   *
   * @param farmer
   * @return
   */
  @Mapping(source = "region.id", target = "registrationRegion")
  @Mapping(source = "fields", target = "regionIds")
  FarmerDTO toDTO(Farmer farmer);


  @Mapping(source = "region.id", target = "registrationRegion")
  @Mapping(source = "fields", target = "regionIds")
  FarmerDTO toDTO(FarmerInArchive farmer);


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
  void updateEntity(CreateFarmerDTO farmerDTO, @MappingTarget Farmer farmer);


}
