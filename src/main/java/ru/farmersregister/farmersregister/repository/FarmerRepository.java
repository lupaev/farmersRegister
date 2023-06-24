package ru.farmersregister.farmersregister.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Farmer;

/**
 * Репозиторий для сущности фермера
 */
@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

  /**
   * Сохранение в БД районы посевных полей фермера
   * @param farmerId
   * @param regionId
   */
  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "insert into farmer_regions (farmer_id, regions_id) "
      + "values (:farmerId, :regionId)")
  void saveFarmerFieldInOtherRegions(@Param("farmerId") Long farmerId,
      @Param("regionId") Long regionId);

  /**
   * Поиск фермера в БД по ИНН и наименованию (Обязательные поля при создании фермера)
   * @param inn
   * @param name
   * @return
   */
  Farmer findByInnAndName(Integer inn, String name);

}


