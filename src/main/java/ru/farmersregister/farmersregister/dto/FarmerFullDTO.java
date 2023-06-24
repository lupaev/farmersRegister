package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Полная сущность фермера")
public class FarmerFullDTO {

  @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;

  @Schema(description = "Наименование")
  private String name;

  @Schema(description = "Организационно-правовая форма")
  private LegalForm legalForm;

  @Schema(description = "ИНН")
  private Integer inn;

  @Schema(description = "КПП")
  private Integer kpp;

  @Schema(description = "ОГРН")
  private Integer ogrn;

  @Schema(description = "Наименование района регистрации фермера")
  private String registrationRegionName;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Schema(description = "Дата регистрации")
  private LocalDate dateRegistration;

  @Schema(description = "Статус активности/архивности")
  private Status status;

  @Schema(description = "Районы посевных полей")
  private Collection<Long> fields;

}
