package ru.farmersregister.farmersregister.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.sql.SQLException;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO;
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
  public ResponseEntity<Page<RegionDTO>> findAll(@RequestBody RequestDTO requestDTO,
      Pageable pageable) {
    return ResponseEntity.ok(regionService.findAll(requestDTO, pageable));
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
  public ResponseEntity<Page<RegionDTO>> findAllInArchive(Pageable pageable) {
    return ResponseEntity.ok(regionService.findAllInArchive(pageable));
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
  public ResponseEntity<RegionDTO> addRegion(@RequestBody RegionDTO regionDTO) {
    return ResponseEntity.ok(regionService.addRegion(regionDTO));
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
      @RequestBody RegionDTO regionDTO) {
    return ResponseEntity.ok(regionService.patchRegion(id, regionDTO));
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
