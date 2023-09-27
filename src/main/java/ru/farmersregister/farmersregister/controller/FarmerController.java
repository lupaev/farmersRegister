package ru.farmersregister.farmersregister.controller;

import com.querydsl.core.types.Predicate;
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
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

import javax.validation.constraints.Min;
import java.sql.SQLException;
import java.util.Collection;

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
  @GetMapping
  public ResponseEntity<Page<FarmerDTO>> findAll(
      @QuerydslPredicate(root = Farmer.class, bindings = FarmerRepository.class)
      Predicate predicate, Pageable pageable) {
    return ResponseEntity.ok(farmerService.findAll(predicate, pageable));
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
  public ResponseEntity<Collection<FarmerDTO>> findAllInArchive() {
    return ResponseEntity.ok(farmerService.findAllInArchive());
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
  public ResponseEntity<FarmerDTO> addFarmer(@RequestBody CreateFarmerDTO createFarmerDTO) {
    return ResponseEntity.ok(farmerService.addFarmer(createFarmerDTO));
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
      @RequestBody CreateFarmerDTO createFarmerDTO) {
    return ResponseEntity.ok(farmerService.patchFarmer(id, createFarmerDTO));
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
