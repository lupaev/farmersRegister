package ru.farmersregister.farmersregister.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Сервис для сущности фермеров
 */
public interface FarmerService {

  /**
   * Получение всех Фермеров
   *
   * @return
   */
  Page<FarmerDTO> findAll(Predicate predicate, Pageable pageable);

  /**
   * Получение всех Фермеров в Архиве
   *
   * @return
   */
  Collection<FarmerDTO> findAllInArchive();

  /**
   * Добавление нового Фермера в БД
   *
   * @param createFarmerDTO
   * @return
   */
  FarmerDTO addFarmer(CreateFarmerDTO createFarmerDTO);

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
   * @param createFarmerDTO
   * @return
   */
  FarmerDTO patchFarmer(Long id, CreateFarmerDTO createFarmerDTO);

  /**
   * Перемещение Фермера в архив
   *
   * @param id
   * @return
   * @throws SQLException
   */
  FarmerDTO delFarmer(Long id) throws SQLException;
}
