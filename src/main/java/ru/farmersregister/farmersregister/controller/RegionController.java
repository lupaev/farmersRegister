package ru.farmersregister.farmersregister.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.SortRegion;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.service.RegionService;

@RestController
@RequestMapping("/region")
@Tag(name = "Районы", description = "Контроллер для операций с районами")
@Slf4j
public class RegionController {

  private final RegionService regionService;

  public RegionController(RegionService regionService) {
    this.regionService = regionService;
  }

  @Operation(summary = "Список всех районов",
      description = "Получение списка всех районов по выбранному фильтру."
      + "NAME - возвращает список отсортированный по имени. "
      + "CODE - возвращает список отсортированный по коду районов. "
      + "NONACTIVE - возвращает список районов в архиве. "
      + "ALL - возвращает список всех районов вне зависимости от статуса")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = RegionDTO.class)))
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
  public ResponseEntity<Collection<RegionDTO>> findAll(
      @RequestParam(name = "sort by") SortRegion sortRegion) {
    return ResponseEntity.ok(regionService.findAll(sortRegion));
  }

  @Operation(summary = "Добавление района")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = RegionDTO.class)))
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
  public ResponseEntity<RegionDTO> addRegion(
      @RequestParam(name = "name")
      @Parameter(description = "Наименование района") String name,
      @RequestParam(name = "code", required = false)
      @Parameter(description = "Код района") Integer codeRegion,
      @RequestParam(name = "status", required = false)
      @Parameter(description = "Статус активности/архивности") Status status) {
    return ResponseEntity.ok(regionService.addRegion(name, codeRegion, status));
  }

  @Operation(summary = "Изменение данных района. ",
      description = "Для отправки в архив необходимо изменить статус на NONACTIVE.")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = RegionDTO.class)))
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
  public ResponseEntity<RegionDTO> patchRegion(
      @PathVariable(name = "id") @Parameter(description = "Идентификатор") Long id,
      @RequestParam(name = "name", required = false)
      @Parameter(description = "Наименование района") String name,
      @RequestParam(name = "code", required = false)
      @Parameter(description = "Код района") Integer codeRegion,
      @RequestParam(name = "status", required = false)
      @Parameter(description = "Статус активности/архивности") Status status){
    return ResponseEntity.ok(regionService.patchRegion(id, name, codeRegion, status));
  }


}
