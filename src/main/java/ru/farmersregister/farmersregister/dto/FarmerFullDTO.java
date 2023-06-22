package ru.farmersregister.farmersregister.dto;

import java.time.LocalDate;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.Status;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FarmerFullDTO {

  private Long id;

  private String name;

  private LegalForm legalForm;

  private Integer inn;

  private Integer kpp;

  private Integer ogrn;

  private String registrationRegionName;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateRegistration;

  private Status status;

  private Collection<Long> fields;

}
