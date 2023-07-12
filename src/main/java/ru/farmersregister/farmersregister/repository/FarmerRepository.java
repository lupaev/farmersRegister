package ru.farmersregister.farmersregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Farmer;

/**
 * Репозиторий для сущности фермера
 */
@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {



  /**
   * Поиск фермера в БД по ИНН и наименованию (Обязательные поля при создании фермера)
   * @param inn
   * @param name
   * @return
   */
  Farmer findByInnAndName(String inn, String name);

}


