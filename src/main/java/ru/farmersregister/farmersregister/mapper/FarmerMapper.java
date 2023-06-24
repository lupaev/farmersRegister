package ru.farmersregister.farmersregister.mapper;

import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;

/**
 * Маппер для фермера
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FarmerMapper {

  /**
   * Преобразование DTO в сущность фермера
   * @param farmerDTO
   * @return
   */
  @Mapping(target = "region.id", source = "registrationRegion")
  Farmer toEntity(FarmerDTO farmerDTO);

  /**
   * Преобразование сущности фермера в DTO
   * @param farmer
   * @return
   */
  default FarmerDTO toDTO(Farmer farmer) {
    FarmerDTO farmerDTO = new FarmerDTO();
    farmerDTO.setId(farmer.getId());
    farmerDTO.setName(farmer.getName());
    farmerDTO.setLegalForm(farmer.getLegalForm());
    farmerDTO.setInn(farmer.getInn());
    farmerDTO.setKpp(farmer.getKpp());
    farmerDTO.setOgrn(farmer.getOgrn());
    farmerDTO.setRegistrationRegion(farmer.getRegion().getId());
    farmerDTO.setDateRegistration(farmer.getDateRegistration());
    farmerDTO.setStatus(farmer.getStatus());
    farmerDTO.setFields(
        farmer.getRegions().stream().map(Region::getId).collect(Collectors.toList()));
    return farmerDTO;
  }

  /**
   * Преобразование коллекции фермеров в коллекцию  DTO
   * @param list
   * @return
   */
  Collection<FarmerDTO> toDTOList(Collection<Farmer> list);

  /**
   * Обновление сущности фермера из данных DTO
   * @param farmerDTO
   * @param farmer
   */
  void updateEntity(FarmerDTO farmerDTO, @MappingTarget Farmer farmer);


}
