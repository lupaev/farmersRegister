package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Сущность для создания района")
public class CreateRegionDTO {

    @Schema(description = "Наименование")
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @Schema(description = "Код региона")
    @NotNull
    @Min(1)
    @Digits(message = "трехзначное максимальное число номер региона", integer = 3, fraction = 0)
    private Integer codeRegion;
}
