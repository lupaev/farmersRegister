package ru.farmersregister.farmersregister.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.service.FarmerService;

import javax.validation.constraints.Min;
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

  @GetMapping
  public ResponseEntity<Collection<FarmerDTO>> findAll() {
    return ResponseEntity.ok(farmerService.findAll());
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


}
