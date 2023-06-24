package ru.farmersregister.farmersregister.entity;


import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Сущность фермера
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "farmer")
@Entity
public class Farmer {

  /**
   * Идентификатор
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Наименование организации
   */
  @Column(name = "name")
  private String name;

  /**
   * Организационно-правовая форма
   */
  @Column(name = "legal_form")
  @Enumerated(EnumType.STRING)
  private LegalForm legalForm;

  /**
   * ИНН
   */
  @Column(name = "inn")
  private Integer inn;

  /**
   * КПП
   */
  @Column(name = "kpp")
  private Integer kpp;

  /**
   * ОГРН
   */
  @Column(name = "ogrn")
  private Integer ogrn;

  /**
   * Дата регистрации
   */
  @Column(name = "date_registration")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateRegistration;

  /**
   * Статус активности/архивности
   */
  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status;

  /**
   * Район регистрации фермера
   */
  @ManyToOne()
  @JoinColumn(name = "registration_region_id")
  private Region region;

  /**
   * Районы посевных полей
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Collection<Region> regions;

}
