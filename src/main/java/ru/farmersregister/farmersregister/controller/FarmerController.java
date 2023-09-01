package ru.farmersregister.farmersregister.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO;
import ru.farmersregister.farmersregister.service.FarmerService;

import javax.validation.constraints.Min;
import java.sql.SQLException;

@RestController
@RequestMapping("/farmer")
@Tag(name = "Фермеры", description = "Контроллер для операций с фермерами")
@Slf4j
public class FarmerController {

  private final FarmerService farmerService;

  public FarmerController(FarmerService farmerService) {
    this.farmerService = farmerService;
  }

  @Operation(summary = "Список всех фермеров")
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
  @PostMapping
  public ResponseEntity<Page<FarmerDTO>> findAll(@RequestBody RequestDTO requestDTO,
      Pageable pageable) {
    return ResponseEntity.ok(farmerService.findAll(requestDTO, pageable));
  }

  @Operation(summary = "Список всех фермеров в архиве")
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
  @GetMapping(value = "/archived")
  public ResponseEntity<Page<FarmerDTO>> findAllInArchive(Pageable pageable) {
    return ResponseEntity.ok(farmerService.findAllInArchive(pageable));
  }

  @Operation(summary = "Получение данных фермера по идентификатору")
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
  @GetMapping(value = "/{id}")
  public ResponseEntity<FarmerDTO> getFarmer
      (
          @PathVariable(name = "id")
          @Parameter(description = "Идентификатор", example = "1") @Min(1) Long id
      ) {
    return ResponseEntity.ok(farmerService.getFarmer(id));
  }

  @Operation(summary = "Добавление в БД нового фермера")
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
  public ResponseEntity<FarmerDTO> addFarmer(@RequestBody FarmerDTO farmerDTO) {
    return ResponseEntity.ok(farmerService.addFarmer(farmerDTO));
  }

  @Operation(summary = "Изменение данных фермера.")
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
  public ResponseEntity<FarmerDTO> patchFarmer(@PathVariable(name = "id")
  @Parameter(description = "Идентификатор", example = "1") @Min(1) Long id,
      @RequestBody FarmerDTO farmerDTO) {
    return ResponseEntity.ok(farmerService.patchFarmer(id, farmerDTO));
  }


  @Operation(summary = "Отправка в ахив")
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
  @DeleteMapping(value = "/del/{id}")
  public ResponseEntity<FarmerDTO> delRegion(@PathVariable(name = "id")
  @Parameter(description = "Идентификатор", example = "1") @Min(1) Long id)
      throws SQLException {
    return ResponseEntity.ok(farmerService.delFarmer(id));
  }


}
