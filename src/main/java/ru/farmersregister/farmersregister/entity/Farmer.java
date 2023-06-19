package ru.farmersregister.farmersregister.entity;


import java.time.LocalDate;
import java.util.Collection;
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
public class Farmer {

  private String name;
  private LegalForm legalForm;
  private int inn;
  private int kpp;
  private int ogrn;
  private Region region;// id регистрации района
  private Collection<Region> regionCollection;
  private LocalDate dateRegistration;
  private ArchiveStatus archiveStatus;

}
