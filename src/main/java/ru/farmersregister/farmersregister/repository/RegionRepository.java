package ru.farmersregister.farmersregister.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Region;

/**
 * Репозиторий для сущности района
 */
@Repository
@Transactional
public interface RegionRepository extends JpaRepository<Region, Long> {

  /**
   * Поиск по наименованию и ИНН
   * @param name
   * @param code
   * @return
   */
  Region findByNameAndCodeRegion(String name, Integer code);

  /**
   * Перенесение в архив
   * @param id
   */
  @Modifying
  @Query(value = "INSERT INTO region_archive SELECT * FROM region where region.id = :id", nativeQuery = true)
  void saveToArchive(@Param("id") Long id);

}
