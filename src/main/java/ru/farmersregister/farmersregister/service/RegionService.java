package ru.farmersregister.farmersregister.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.exception.ElementNotFound;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Сервис для сущности районов
 */
public interface RegionService {

  /**
   * Получение всех Районов
   *
   * @return
   */
  Page<RegionDTO> findAll(Predicate predicate, Pageable pageable);

  /**
   * Получение всех Районов в Архиве
   *
   * @return
   */
  Collection<RegionDTO> findAllInArchive();

  /**
   * Добавление нового Района в БД
   *
   * @param regionDTO
   * @return
   */
  RegionDTO addRegion(RegionDTO regionDTO);

  /**
   * Изменение данных Района
   *
   * @param id
   * @param regionDTO
   * @return
   * @throws ElementNotFound
   */
  RegionDTO patchRegion(Long id, RegionDTO regionDTO) throws ElementNotFound;

  /**
   * Перемещение Района в архив
   *
   * @param id
   * @return
   * @throws SQLException
   */
  RegionDTO delRegion(Long id) throws SQLException;

}
