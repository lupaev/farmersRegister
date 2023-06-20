package ru.farmersregister.farmersregister.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.service.FarmerService;

@RestController
@RequestMapping("/farmer")
@Tag(name = "Фермеры")
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
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error"
      ),
  })
  @GetMapping
  public ResponseEntity<Collection<FarmerDTO>> findAll() {
    return ResponseEntity.ok(farmerService.findAll());
  }

  @Operation(summary = "Добавление фермера")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error"
      ),
  })
  @PostMapping(value = "/add")
  public ResponseEntity<FarmerDTO> addFarmer(FarmerDTO farmerDTO) {
    return ResponseEntity.ok(farmerService.addFarmer(farmerDTO));
  }


}
