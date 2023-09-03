package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Сущность района")
public class RegionDTO {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Наименование")
    private String name;

    @Schema(description = "Код региона")
    private Integer codeRegion;
}
