package ru.farmersregister.farmersregister.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.FarmerInArchive;

import javax.transaction.Transactional;

/**
 * Репозиторий для сущности фермера
 */
@Repository
@Transactional
public interface FarmerRepository extends JpaRepository<Farmer, Long>, JpaSpecificationExecutor<Farmer> {


    /**
     * Поиск фермера в БД по ИНН и наименованию (Обязательные поля при создании фермера)
     *
     * @param inn
     * @param name
     * @return
     */
    Farmer findByInnAndName(String inn, String name);

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


    /**
     * Получение всех архивных записей
     *
     * @return
     */
//    @Query(nativeQuery = true, value = "SELECT * FROM farmer_archive as fa JOIN farmer_regions_archive as fra on fa.id = fra.farmer_id")
    @Query(nativeQuery = true, value = "SELECT * FROM farmer_archive")
    Page<FarmerInArchive> findAllInArchive(Pageable pageable);

}


