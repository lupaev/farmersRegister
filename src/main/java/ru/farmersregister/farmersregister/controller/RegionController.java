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
import ru.farmersregister.farmersregister.dto.CreateRegionDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.RegionService;

import javax.validation.constraints.Min;
import java.sql.SQLException;
import java.util.Collection;

@RestController
@RequestMapping("/region")
@Tag(name = "Районы", description = "Контроллер для операций с районами")
@Slf4j
public class RegionController {

  private final RegionService regionService;

  public RegionController(RegionService regionService) {
    this.regionService = regionService;
  }


  @Operation(summary = "Список всех районов")
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
  public ResponseEntity<Page<RegionDTO>> findAll(
      @QuerydslPredicate(root = Region.class, bindings = RegionRepository.class)
      Predicate predicate, Pageable pageable) {
    return ResponseEntity.ok(regionService.findAll(predicate, pageable));
  }

  @Operation(summary = "Список всех районов в архиве")
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
  @GetMapping(value = "/archived")
  public ResponseEntity<Collection<RegionDTO>> findAllInArchive() {
    return ResponseEntity.ok(regionService.findAllInArchive());
  }


  @Operation(summary = "Добавление в БД нового района")
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
  public ResponseEntity<RegionDTO> addRegion(@RequestBody CreateRegionDTO createRegionDTO) {
    return ResponseEntity.ok(regionService.addRegion(createRegionDTO));
  }


  @Operation(summary = "Изменение данных района.")
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
      @PathVariable(name = "id") @Parameter(description = "Идентификатор", example = "1") @Min(1) Long id,
      @RequestBody CreateRegionDTO createRegionDTO) {
    return ResponseEntity.ok(regionService.patchRegion(id, createRegionDTO));
  }

  @Operation(summary = "Отправка в ахив")
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
  @DeleteMapping(value = "/del/{id}")
  public ResponseEntity<RegionDTO> delRegion(
      @PathVariable(name = "id") @Parameter(description = "Идентификатор", example = "1") @Min(1) Long id)
      throws SQLException {
    return ResponseEntity.ok(regionService.delRegion(id));
  }


}
