package ru.farmersregister.farmersregister.service;

import java.sql.SQLException;
import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.exception.ElemNotFound;

/**
 * Сервис для сущности районов
 */
public interface RegionService {

  /**
   * Получение всех Районов
   * @return
   */
  Collection<RegionDTO> findAll();

  /**
   * Добавление нового Района в БД
   * @param regionDTO
   * @return
   */
  RegionDTO addRegion(RegionDTO regionDTO);

  /**
   * Изменение данных Района
   * @param id
   * @param regionDTO
   * @return
   * @throws ElemNotFound
   */
  RegionDTO patchRegion(Long id, RegionDTO regionDTO) throws ElemNotFound;

  /**
   * Перемещение Района в архив
   * @param id
   * @return
   * @throws SQLException
   */
  RegionDTO delRegion(Long id) throws SQLException;

}
