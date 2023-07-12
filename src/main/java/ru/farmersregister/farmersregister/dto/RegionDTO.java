package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Сущность района")
public class RegionDTO {

  @Schema(description = "Идентификатор")
  private Long id;

  @Schema(description = "Наименование")
  private String name;

  @Schema(description = "Код региона")
  private Integer codeRegion;

//  @Schema(description = "Статус активности/архивности")
//  private Status status;


}
