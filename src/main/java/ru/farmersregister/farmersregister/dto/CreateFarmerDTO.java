package ru.farmersregister.farmersregister.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Сущность для создания фермера")
public class CreateFarmerDTO {

    @Schema(description = "Наименование")
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @Schema(description = "Организационно-правовая форма")
    @NotNull
    @NotEmpty
    @NotBlank
    private String legalForm;

    @Schema(description = "ИНН")
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp="\\d{12}")
    private String inn;

    @Schema(description = "КПП")
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp="\\d{9}")
    private String kpp;

    @Schema(description = "ОГРН")
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp="\\d{13}")
    private String ogrn;

    @Schema(description = "Район регистрации фермера")
    @NotNull
    @Min(1)
    @Digits(message = "трехзначное максимальное число номер региона", integer = 3, fraction = 0)
    private Long registrationRegion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Дата регистрации")
    private LocalDate dateRegistration;

    @Schema(description = "Районы посевных полей")
    private Collection<Long> regionIds;

}
