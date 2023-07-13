package ru.farmersregister.farmersregister.service;

import java.sql.SQLException;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO;
import ru.farmersregister.farmersregister.exception.ElemNotFound;

/**
 * Сервис для сущности районов
 */
public interface RegionService {

  /**
   * Получение всех Районов
   * @return
   */
  Page<RegionDTO> findAll(RequestDTO requestDTO, Pageable pageable);

  /**
   * Получение всех Районов в Архиве
   * @return
   */
  Page<RegionDTO> findAllInArchive(Pageable pageable);

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
