package ru.farmersregister.farmersregister.mapper;


import java.util.Collection;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;

public interface FarmerMapper {

  Farmer toEntity(FarmerDTO farmerDTO);

  FarmerDTO toDTO(Farmer farmer);

  Collection<FarmerDTO> toDTOList(Collection<Farmer> list);

}
