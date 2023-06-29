package ru.farmersregister.farmersregister.entity;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

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
  private long inn;

  /**
   * КПП
   */
  @Column(name = "kpp")
  private long kpp;

  /**
   * ОГРН
   */
  @Column(name = "ogrn")
  private long ogrn;

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
  @ManyToMany
  @JoinTable (name="farmer_regions",
          joinColumns=@JoinColumn (name="farmer_id"),
          inverseJoinColumns=@JoinColumn(name="regions_id"))
  private Collection<Region> fields;

}
