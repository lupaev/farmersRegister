package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Collection;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Сущность фермера")
public class FarmerDTO {

  @Schema(description = "Идентификатор")
  @Nullable
  private Long id;

  @Schema(description = "Наименование")
  @Nullable
  private String name;

  @Schema(description = "Организационно-правовая форма")
  @Nullable
  private String legalForm;

  @Schema(description = "ИНН")
  @Nullable
  private String inn;

  @Schema(description = "КПП")
  @Nullable
  private String kpp;

  @Schema(description = "ОГРН")
  @Nullable
  private String ogrn;

  @Schema(description = "Район регистрации фермера")
  @Nullable
  private Long registrationRegion;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Schema(description = "Дата регистрации")
  @Nullable
  private LocalDate dateRegistration;

//  @Schema(description = "Статус активности/архивности")
//  @Nullable
//  private Status status;

  @Schema(description = "Районы посевных полей")
  @Nullable
  private Collection<RegionDTO> fields;

}
