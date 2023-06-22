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

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FarmerMapper {

  @Mapping(target = "region.id", source = "registrationRegion")
  Farmer toEntity(FarmerDTO farmerDTO);

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

  Collection<FarmerDTO> toDTOList(Collection<Farmer> list);

  void updateEntity(FarmerDTO farmerDTO, @MappingTarget Farmer farmer);


}
