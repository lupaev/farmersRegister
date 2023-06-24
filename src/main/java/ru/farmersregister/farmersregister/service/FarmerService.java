package ru.farmersregister.farmersregister.service;

import java.time.LocalDate;
import java.util.Collection;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.exception.ElemNotFound;

/**
 * Сервис для сущности фермеров
 */
public interface FarmerService {

  /**
   * Метод получения из БД всех активных фермеров
   * @param sortFarmer
   * @return
   */
  Collection<FarmerDTO> findAll(SortFarmer sortFarmer);

  /**
   * Добавление в БД нового фермера
   * @param name
   * @param legalForm
   * @param inn
   * @param kpp
   * @param ogrn
   * @param dateRegistration
   * @param status
   * @param registrationRegion
   * @param regionId
   * @return
   */
  FarmerDTO addFarmer(String name, LegalForm legalForm, Integer inn, Integer kpp, Integer ogrn,
      LocalDate dateRegistration, Status status, Long registrationRegion, Long regionId);

  /**
   * Изменение данных фермера в БД
   * @param id
   * @param name
   * @param legalForm
   * @param inn
   * @param kpp
   * @param ogrn
   * @param dateRegistration
   * @param status
   * @param registrationRegion
   * @return
   * @throws ElemNotFound
   */
  FarmerDTO patchFarmer(Long id, String name, LegalForm legalForm, Integer inn, Integer kpp,
      Integer ogrn, LocalDate dateRegistration, Status status, Long registrationRegion)
      throws ElemNotFound;

  /**
   * Метод получения полных данных о фермере
   * @param id
   * @return
   */
  FarmerFullDTO getFarmer(Long id);

}
