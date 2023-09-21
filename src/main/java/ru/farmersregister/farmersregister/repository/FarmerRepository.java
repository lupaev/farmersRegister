package ru.farmersregister.farmersregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Farmer;

import javax.transaction.Transactional;

/**
 * Репозиторий для сущности фермера
 */
@Repository
@Transactional
public interface FarmerRepository extends JpaRepository<Farmer, Long>, JpaSpecificationExecutor<Farmer> {

    /**
     * Перенесение в архив
     *
     * @param id
     */
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO farmer_archive SELECT * FROM farmer where farmer.id = :id")
    void saveToArchive(@Param("id") Long id);

    /**
     * Перенесение в архив даных о посевных полях
     */
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO farmer_regions_archive SELECT * FROM farmer_regions WHERE farmer_id = :id")
    void saveFarmerFieldsToArchive(@Param("id") Long id);


}


