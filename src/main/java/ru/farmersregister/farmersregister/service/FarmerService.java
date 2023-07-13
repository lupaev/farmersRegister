package ru.farmersregister.farmersregister.service;

import java.sql.SQLException;
import ru.farmersregister.farmersregister.dto.FarmerDTO;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;

/**
 * Сервис для сущности фермеров
 */
public interface FarmerService {

  /**
   * Получение всех Фермеров
   * @return
   */
  Collection<FarmerDTO> findAll();

  /**
   * Добавление нового Фермера в БД
   * @param farmerDTO
   * @return
   */
  FarmerDTO addFarmer(FarmerDTO farmerDTO);

  /**
   * Получение Фермера по идентификатору
   * @param id
   * @return
   */
  FarmerDTO getFarmer(Long id);

  /**
   * Изменение данных Фермера
   * @param id
   * @param farmerDTO
   * @return
   */
  FarmerDTO patchFarmer(Long id, FarmerDTO farmerDTO);

  /**
   * Перемещение Фермера в архив
   * @param id
   * @return
   * @throws SQLException
   */
  FarmerDTO delFarmer(Long id) throws SQLException;
}
