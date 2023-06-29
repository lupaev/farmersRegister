package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.Status;

import java.time.LocalDate;
import java.util.Collection;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Полная сущность фермера")
public class FarmerFullDTO {

//  @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;

//  @Schema(description = "Наименование")
  private String name;

//  @Schema(description = "Организационно-правовая форма")
  private LegalForm legalForm;

//  @Schema(description = "ИНН")
  private long inn;

//  @Schema(description = "КПП")
  private long kpp;

//  @Schema(description = "ОГРН")
  private long ogrn;

//  @Schema(description = "Наименование района регистрации фермера")
  private String registrationRegionName;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
//  @Schema(description = "Дата регистрации")
  private LocalDate dateRegistration;

//  @Schema(description = "Статус активности/архивности")
  private Status status;

//  @Schema(description = "Районы посевных полей")
  private Collection<Long> fields;

}
