package ru.farmersregister.farmersregister.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Region;

import javax.transaction.Transactional;

/**
 * Репозиторий для сущности района
 */
@Repository
@Transactional
public interface RegionRepository extends JpaRepository<Region, Long>,
        JpaSpecificationExecutor<Region> {

    /**
     * Перенесение в архив
     *
     * @param id
     */
    @Modifying
    @Query(value = "INSERT INTO region_archive SELECT * FROM region where region.id = :id", nativeQuery = true)
    void saveToArchive(@Param("id") Long id);

    /**
     * Получение всех архивных записей
     *
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM region_archive")
    Page<Region> findAllInArchive(Pageable pageable);
}
