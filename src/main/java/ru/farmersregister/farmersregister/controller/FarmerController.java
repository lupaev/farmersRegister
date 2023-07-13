package ru.farmersregister.farmersregister.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.sql.SQLException;
import java.util.Collection;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.service.FarmerService;

@RestController
@RequestMapping("/farmer")
@Tag(name = "Фермеры", description = "Контроллер для операций с фермерами")
@Slf4j
public class FarmerController {

  private final FarmerService farmerService;

  public FarmerController(FarmerService farmerService) {
    this.farmerService = farmerService;
  }

  @GetMapping
  public ResponseEntity<Collection<FarmerDTO>> findAll() {
    return ResponseEntity.ok(farmerService.findAll());
  }

  @GetMapping(value = "/archived")
  public ResponseEntity<Collection<FarmerDTO>> findAllInArchive() {
    return ResponseEntity.ok(farmerService.findAllInArchive());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<FarmerDTO> getFarmer
      (
          @PathVariable(name = "id")
          @Parameter(description = "Идентификатор", example = "1") @Min(1) Long id
      ) {
    return ResponseEntity.ok(farmerService.getFarmer(id));
  }

  @PostMapping(value = "/add")
  public ResponseEntity<FarmerDTO> addFarmer(@RequestBody FarmerDTO farmerDTO) {
    return ResponseEntity.ok(farmerService.addFarmer(farmerDTO));
  }

  @PatchMapping(value = "/patch/{id}")
  public ResponseEntity<FarmerDTO> patchFarmer(@PathVariable(name = "id") Long id, @RequestBody FarmerDTO farmerDTO) {
    return ResponseEntity.ok(farmerService.patchFarmer(id, farmerDTO));
  }

  @DeleteMapping(value = "/del/{id}")
  public ResponseEntity<FarmerDTO> delRegion(@PathVariable(name = "id") Long id)
      throws SQLException {
    return ResponseEntity.ok(farmerService.delFarmer(id));
  }


}
