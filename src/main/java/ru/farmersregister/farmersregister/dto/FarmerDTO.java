package ru.farmersregister.farmersregister.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.Status;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FarmerDTO {

//  @JsonIgnore
  private Long id;

  private String name;

  private LegalForm legalForm;

  private Integer inn;

  private Integer kpp;

  private Integer ogrn;

  @JsonIgnore
  private Region region;

  @JsonIgnore
  private Collection<Region> regionCollection;

  private LocalDate dateRegistration;

  private Status status;


}
