package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
  private Long id;

  @Schema(description = "Наименование")
  private String name;

  @Schema(description = "Организационно-правовая форма")
  private String legalForm;

  @Schema(description = "ИНН")
  private String inn;

  @Schema(description = "КПП")
  private String kpp;

  @Schema(description = "ОГРН")
  private String ogrn;

  @Schema(description = "Район регистрации фермера")
  private Long registrationRegion;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Schema(description = "Дата регистрации")
  private LocalDate dateRegistration;

  @Schema(description = "Районы посевных полей")
  private Collection<Long> regionIds;

}
