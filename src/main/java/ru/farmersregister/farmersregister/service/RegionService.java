package ru.farmersregister.farmersregister.service;

import java.util.Collection;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.SortRegion;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.exception.ElemNotFound;

/**
 * Сервис для сущности районов
 */
public interface RegionService {

  /**
   * Метод получения из БД всех активных районов
   * @param sortRegion
   * @return
   */
  Collection<RegionDTO> findAll(SortRegion sortRegion);

  /**
   * Метод добавления нового района в БД
   * @param name
   * @param codeRegion
   * @param status
   * @return
   */
  RegionDTO addRegion(String name, Integer codeRegion, Status status);

  /**
   * Изменение данных района в БД
   * @param id
   * @param name
   * @param codeRegion
   * @param status
   * @return
   * @throws ElemNotFound
   */
  RegionDTO patchRegion(Long id, String name, Integer codeRegion, Status status) throws ElemNotFound;

}
