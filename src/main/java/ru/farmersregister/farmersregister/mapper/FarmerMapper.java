package ru.farmersregister.farmersregister.mapper;


import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FarmerMapper {

  Farmer toEntity(FarmerDTO farmerDTO);

  FarmerDTO toDTO(Farmer farmer);

  Collection<FarmerDTO> toDTOList(Collection<Farmer> list);

}
