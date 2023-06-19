package ru.farmersregister.farmersregister.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "farmer")
@Entity
public class Farmer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "legal_form")
  @Enumerated(EnumType.STRING)
  private LegalForm legalForm;

  @Column(name = "inn")
  private int inn;

  @Column(name = "kpp")
  private int kpp;

  @Column(name = "ogrn")
  private int ogrn;

  @JoinColumn(name = "registration_region_id")
  @ManyToOne
  private Region region;

  @ManyToMany
  private Collection<Region> regionCollection;

  @Column(name = "date_registration")
  private LocalDate dateRegistration;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status;

}