package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Сущность для создания района")
public class CreateRegionDTO {

  @Schema(description = "Наименование")
  private String name;

  @Schema(description = "Код региона")
  private Integer codeRegion;
}
