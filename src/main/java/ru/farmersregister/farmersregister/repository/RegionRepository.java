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

  Region findByNameAndCodeRegion(String name, Integer code);
  @Modifying
  @Query(value = "INSERT INTO region_archive (name, code_region) SELECT region.name, region.code_region FROM region where region.id = :id", nativeQuery = true)
  void saveToArchive(@Param("id") Long id);

}
