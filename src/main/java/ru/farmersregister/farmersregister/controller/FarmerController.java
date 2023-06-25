package ru.farmersregister.farmersregister.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.Collection;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.service.FarmerService;

@RestController
@RequestMapping("/farmer")
@Tag(name = "Фермеры", description = "Контроллер для операций с фермерами")
@Slf4j
public class FarmerController {

  @Autowired
  private FarmerService farmerService;

  @Operation(summary = "Список всех фермеров",
      description = "Получение списка всех фермеров по выбранному фильтру."
          + "NAME - возвращает список отсортированный по имени. "
          + "INN - возвращает список отсортированный по ИНН. "
          + "REGISTRATION - возвращает список отсортированный по идентификатору района. "
          + "DATE - возвращает список отсортированный по дате регистрации. "
          + "ACTIVE - возвращает список актуальных фермеров. "
          + "NONACTIVE - возвращает список фермеров в архиве. "
          + "ALL - возвращает список всех фермеров вне зависимости от статуса")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = FarmerDTO.class)))
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error",
          content = @Content(schema = @Schema())
      ),
  })
  @GetMapping
  public ResponseEntity<Collection<FarmerDTO>> findAll
      (
          @RequestParam("sort")
          @Parameter(description = "Выбор способа сортировки",
              example = "NAME") SortFarmer sortFarmer
      ) {
    return ResponseEntity.ok(farmerService.findAll(sortFarmer));
  }

  @Operation(summary = "Получение данных фермера по идентификатору")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = FarmerFullDTO.class)))
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error",
          content = @Content(schema = @Schema())
      ),
  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<FarmerFullDTO> getFarmer
      (
          @PathVariable(name = "id")
          @Parameter(description = "Идентификатор", example = "1") @Min(1) Long id
      ) {
    return ResponseEntity.ok(farmerService.getFarmer(id));
  }

  @Operation(summary = "Добавление в БД нового фермера",
      description = "Для отправки в архив необходимо изменить статус на NONACTIVE.")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = FarmerDTO.class)))
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error",
          content = @Content(schema = @Schema())
      ),
  })
  @PostMapping(value = "/add")
  public ResponseEntity<FarmerDTO> addFarmer
      (
          @RequestParam(name = "name") @Parameter(description = "Наименование организации") String name,
          @RequestParam(name = "legal form", required = false)
          @Parameter(description = "Организационно-правовая форма") LegalForm legalForm,
          @RequestParam(name = "inn") @Parameter(description = "ИНН") Integer inn,
          @RequestParam(name = "kpp", required = false) @Parameter(description = "КПП") Integer kpp,
          @RequestParam(name = "ogrn", required = false) @Parameter(description = "ОГРН") Integer ogrn,
          @RequestParam(name = "date registration", required = false)
          @Parameter(description = "Дата регистрации", example = "2022-02-22")
          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateRegistration,
          @RequestParam(name = "status", required = false)
          @Parameter(description = "Статус активности/архивности") Status status,
          @RequestParam(name = "registration region", required = false)
          @Parameter(description = "Идентификатор района регистрации") Long registrationRegion,
          @RequestParam(name = "region_id", required = false)
          @Parameter(description = "Районы посевных полей") Long regionId) {
    return ResponseEntity.ok(farmerService.addFarmer(name, legalForm, inn, kpp, ogrn,
        dateRegistration, status, registrationRegion, regionId));
  }

  @Operation(summary = "Изменение данных фермера.",
      description = "Изменение данных фермера. "
          + "id - Идентификатор. "
          + "name - Наименование. "
          + "legal form - Организационно-правовая форма. "
          + "inn - ИНН. "
          + "kpp - КПП. "
          + "ogrn - ОГРН. "
          + "date registration - Дата регистрации. "
          + "status - Статус активности/архивности. Для отправки в архив необходимо изменить "
          + "статус на NONACTIVE. "
          + "registration region - Районы посевных полей. ")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = FarmerDTO.class)))
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error",
          content = @Content(schema = @Schema())
      ),
  })
  @PatchMapping(value = "/patch/{id}")
  public ResponseEntity<FarmerDTO> patchFarmer(
      @PathVariable(name = "id")
      @Parameter(description = "Идентификатор") long id,
      @RequestParam(name = "name", required = false)
      @Parameter(description = "Наименование организации") String name,
      @RequestParam(name = "legal form", required = false)
      @Parameter(description = "Организационно-правовая форма") LegalForm legalForm,
      @RequestParam(name = "inn", required = false)
      @Parameter(description = "ИНН") Integer inn,
      @RequestParam(name = "kpp", required = false)
      @Parameter(description = "КПП") Integer kpp,
      @RequestParam(name = "ogrn", required = false)
      @Parameter(description = "ОГРН") Integer ogrn,
      @RequestParam(name = "date registration", required = false)
      @Parameter(description = "Дата регистрации", example = "2022-02-22") LocalDate dateRegistration,
      @RequestParam(name = "status", required = false)
      @Parameter(description = "Статус активности/архивности") Status status,
      @RequestParam(name = "registration region", required = false)
      @Parameter(description = "Идентификатор района регистрации") Long registrationRegion) {
    return ResponseEntity.ok(farmerService.patchFarmer(id, name, legalForm, inn, kpp, ogrn,
        dateRegistration, status, registrationRegion));
  }


}
