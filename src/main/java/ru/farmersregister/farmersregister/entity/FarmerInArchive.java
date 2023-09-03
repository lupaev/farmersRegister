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
@Table(name = "farmer_archive")
@Entity
public class FarmerInArchive {

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
  private String legalForm;

  /**
   * ИНН
   */
  @Column(name = "inn")
  private String inn;

  /**
   * КПП
   */
  @Column(name = "kpp")
  private String kpp;

  /**
   * ОГРН
   */
  @Column(name = "ogrn")
  private String ogrn;

  /**
   * Дата регистрации
   */
  @Column(name = "date_registration")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateRegistration;


  /**
   * Район регистрации фермера
   */
  @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
  @JoinColumn(name = "registration_region_id")
  private Region region;

  /**
   * Районы посевных полей
   */
  @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
  @JoinTable(name = "farmer_regions_archive",
      joinColumns = @JoinColumn(name = "farmer_id"),
      inverseJoinColumns = @JoinColumn(name = "regions_id"))
  private Collection<Region> fields;

}
