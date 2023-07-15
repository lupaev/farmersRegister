package ru.farmersregister.farmersregister.service;

import com.querydsl.core.types.Predicate;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.farmersregister.farmersregister.dto.FarmerDTO;

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
   * @param farmerDTO
   * @return
   */
  FarmerDTO addFarmer(FarmerDTO farmerDTO);

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
  FarmerDTO patchFarmer(Long id, FarmerDTO farmerDTO);

  /**
   * Перемещение Фермера в архив
   *
   * @param id
   * @return
   * @throws SQLException
   */
  FarmerDTO delFarmer(Long id) throws SQLException;
}
