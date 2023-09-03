package ru.farmersregister.farmersregister.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO;

import java.sql.SQLException;

/**
 * Сервис для сущности фермеров
 */
public interface FarmerService {

  /**
   * Получение всех Фермеров
   *
   * @return
   */
  Page<FarmerDTO> findAll(RequestDTO requestDTO, Pageable pageable);

  /**
   * Получение всех Фермеров в Архиве
   *
   * @return
   */
  Page<FarmerDTO> findAllInArchive(Pageable pageable);

  /**
   * Добавление нового Фермера в БД
   *
   * @param farmerDTO
   * @return
   */
  FarmerDTO addFarmer(CreateFarmerDTO farmerDTO);

  /**
   * Получение Фермера по идентификатору
   *
   * @param id
   * @return
   */
  FarmerDTO getFarmer(Long id);

  /**
   * Изменение данных Фермера
   *
   * @param id
   * @param farmerDTO
   * @return
   */
  FarmerDTO patchFarmer(Long id, CreateFarmerDTO farmerDTO);

  /**
   * Перемещение Фермера в архив
   *
   * @param id
   * @return
   * @throws SQLException
   */
  FarmerDTO delFarmer(Long id) throws SQLException;
}
