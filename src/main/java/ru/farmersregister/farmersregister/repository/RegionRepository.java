package ru.farmersregister.farmersregister.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.QRegion;
import ru.farmersregister.farmersregister.entity.Region;

/**
 * Репозиторий для сущности района
 */
@Repository
@Transactional
public interface RegionRepository extends JpaRepository<Region, Long>,
    JpaSpecificationExecutor<Region>,
    QuerydslPredicateExecutor<Region>, QuerydslBinderCustomizer<QRegion> {

  @Override
  default void customize(QuerydslBindings bindings, QRegion qRegion) {

    bindings.bind(String.class)
        .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    bindings.bind(qRegion.id).all((path, value) -> {
      Iterator<? extends Long> it = value.iterator();
      Long from = it.next();
      if (value.size() >= 2) {
        Long to = it.next();
        return Optional.of(path.between(from, to)); // between
      } else {
        return Optional.of(path.goe(from)); // greater or equal
      }
    });
    bindings.bind(qRegion.codeRegion).all((path, value) -> {
      Iterator<? extends Integer> it = value.iterator();
      Integer from = it.next();
      if (value.size() >= 2) {
        Integer to = it.next();
        return Optional.of(path.between(from, to)); // between
      } else {
        return Optional.of(path.goe(from)); // greater or equal
      }
    });
  }

  /**
   * Поиск по наименованию и ИНН
   *
   * @param name
   * @param code
   * @return
   */
  Region findByNameAndCodeRegion(String name, Integer code);

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
  Collection<Region> findAllInArchive();
}
