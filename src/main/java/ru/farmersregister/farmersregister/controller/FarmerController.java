package ru.farmersregister.farmersregister.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
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
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.entity.Status;
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
  public ResponseEntity<Collection<FarmerDTO>> findAll(SortFarmer sortFarmer) {
    return ResponseEntity.ok(farmerService.findAll(sortFarmer));
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
  public ResponseEntity<FarmerDTO> addFarmer(
      @RequestParam(name = "name") String name,
      @RequestParam(name = "legal form", required = false) LegalForm legalForm,
      @RequestParam(name = "inn", required = false) Integer inn,
      @RequestParam(name = "kpp", required = false) Integer kpp,
      @RequestParam(name = "ogrn", required = false) Integer ogrn,
      @RequestParam(name = "date registration", required = false) LocalDate dateRegistration,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "registration region", required = false) Integer registrationRegion) {
    return ResponseEntity.ok(farmerService.addFarmer(name, legalForm, inn, kpp, ogrn,
        dateRegistration, status, registrationRegion));
  }

  @Operation(summary = "Изменение данных фермера. Отправка в архив(сделать неактивным)")
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
  @PatchMapping(value = "/patch/{id}")
  public ResponseEntity<FarmerDTO> patchFarmer(
      @PathVariable(name = "id") long id,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "legal form", required = false) LegalForm legalForm,
      @RequestParam(name = "inn", required = false) Integer inn,
      @RequestParam(name = "kpp", required = false) Integer kpp,
      @RequestParam(name = "ogrn", required = false) Integer ogrn,
      @RequestParam(name = "date registration", required = false) LocalDate dateRegistration,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "registration region", required = false) Integer registrationRegion)
      throws Exception {
    return ResponseEntity.ok(farmerService.patchFarmer(id, name, legalForm, inn, kpp, ogrn,
        dateRegistration, status, registrationRegion));
  }


}
