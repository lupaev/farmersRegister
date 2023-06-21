package ru.farmersregister.farmersregister.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

  //  @Modifying
  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "insert into farmer_regions (farmer_id, regions_id) values (:farmerId, :regionId)")
  void saveFarmerFieldInOtherRegions(@Param("farmerId") Long farmerId,
      @Param("regionId") Long regionId);

  Farmer findByInnAndName(Integer inn, String name);

}


