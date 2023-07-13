package ru.farmersregister.farmersregister.repository;

import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;

/**
 * Репозиторий для сущности фермера
 */
@Repository
@Transactional
public interface FarmerRepository extends JpaRepository<Farmer, Long>,
    JpaSpecificationExecutor<Farmer> {



  /**
   * Поиск фермера в БД по ИНН и наименованию (Обязательные поля при создании фермера)
   * @param inn
   * @param name
   * @return
   */
  Farmer findByInnAndName(String inn, String name);

  /**
   * Перенесение в архив
   * @param id
   */
  @Modifying
  @Query(value = "INSERT INTO farmer_archive SELECT * FROM farmer where farmer.id = :id", nativeQuery = true)
  void saveToArchive(@Param("id") Long id);

  /**
   * Получение всех архивных записей
   * @return
   */
  @Query(nativeQuery = true, value = "SELECT * FROM farmer_archive")
  Page<Farmer> findAllInArchive(Pageable pageable);

}


