package ru.farmersregister.farmersregister.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import java.time.LocalDate;
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
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.QFarmer;

/**
 * Репозиторий для сущности фермера
 */
@Repository
@Transactional
public interface FarmerRepository extends JpaRepository<Farmer, Long>,
    JpaSpecificationExecutor<Farmer>, QuerydslPredicateExecutor<Farmer>,
    QuerydslBinderCustomizer<QFarmer> {


  @Override
  default void customize(QuerydslBindings bindings, QFarmer qFarmer) {

    bindings.bind(String.class)
        .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    bindings.bind(qFarmer.id).all((path, value) -> {
      Iterator<? extends Long> it = value.iterator();
      Long from = it.next();
      if (value.size() >= 2) {
        Long to = it.next();
        return Optional.of(path.between(from, to)); // between
      } else {
        return Optional.of(path.goe(from)); // greater or equal
      }
    });
    bindings.bind(qFarmer.inn).all((path, value) -> {
      Iterator<? extends String> it = value.iterator();
      Long from = Long.parseLong(it.next());
      if (value.size() >= 2) {
        Long to = Long.parseLong(it.next());
        return Optional.of(path.between(String.valueOf(from), String.valueOf(to))); // between
      } else {
        return Optional.of(path.goe(String.valueOf(from))); // greater or equal
      }
    });
    bindings.bind(qFarmer.kpp).all((path, value) -> {
      Iterator<? extends String> it = value.iterator();
      Long from = Long.parseLong(it.next());
      if (value.size() >= 2) {
        Long to = Long.parseLong(it.next());
        return Optional.of(path.between(String.valueOf(from), String.valueOf(to))); // between
      } else {
        return Optional.of(path.goe(String.valueOf(from))); // greater or equal
      }
    });
    bindings.bind(qFarmer.ogrn).all((path, value) -> {
      Iterator<? extends String> it = value.iterator();
      Long from = Long.parseLong(it.next());
      if (value.size() >= 2) {
        Long to = Long.parseLong(it.next());
        return Optional.of(path.between(String.valueOf(from), String.valueOf(to))); // between
      } else {
        return Optional.of(path.goe(String.valueOf(from))); // greater or equal
      }
    });
    bindings.bind(qFarmer.region.id).all((path, value) -> {
      Iterator<? extends Long> it = value.iterator();
      Long from = it.next();
      if (value.size() >= 2) {
        Long to = it.next();
        return Optional.of(path.between(from, to)); // between
      } else {
        return Optional.of(path.goe(from)); // greater or equal
      }
    });
    bindings.bind(qFarmer.fields.any().id).all((path, value) -> {
      Iterator<? extends Long> it = value.iterator();
      Long from = it.next();
      if (value.size() >= 2) {
        Long to = it.next();
        return Optional.of(path.between(from, to)); // between
      } else {
        return Optional.of(path.goe(from)); // greater or equal
      }
    });
    bindings.bind(qFarmer.dateRegistration).all((path, value) ->
    {
      Iterator<? extends LocalDate> it = value.iterator();
      return Optional.ofNullable(path.between(it.next(), it.next()));
    });
  }


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
  @Query(value = "INSERT INTO farmer_archive SELECT * FROM farmer where farmer.id = :id", nativeQuery = true)
  void saveToArchive(@Param("id") Long id);

  /**
   * Получение всех архивных записей
   *
   * @return
   */
  @Query(nativeQuery = true, value = "SELECT * FROM farmer_archive")
  Collection<Farmer> findAllInArchive();

}


