package ru.farmersregister.farmersregister.controller;


import io.swagger.v3.oas.annotations.Operation;
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
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.SortRegion;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.service.RegionService;

@RestController
@RequestMapping("/region")
@Tag(name = "Районы")
@Slf4j
public class RegionController {

  @Autowired
  private RegionService regionService;

  @Operation(summary = "Список всех районов")
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
  public ResponseEntity<Collection<RegionDTO>> findAll(
      @RequestParam(name = "sort by") SortRegion sortRegion) {
    return ResponseEntity.ok(regionService.findAll(sortRegion));
  }

  @Operation(summary = "Добавление района")
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
  public ResponseEntity<RegionDTO> addRegion(@RequestParam(name = "name") String name,
      @RequestParam(name = "code", required = false) Integer codeRegion,
      @RequestParam(name = "status", required = false) Status status) {
    return ResponseEntity.ok(regionService.addRegion(name, codeRegion, status));
  }

  @Operation(summary = "Изменение данных района. Отправка в архив(сделать неактивным)")
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
  public ResponseEntity<RegionDTO> patchRegion(@PathVariable(name = "id") Long id,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "code", required = false) Integer codeRegion,
      @RequestParam(name = "status", required = false) Status status) throws Exception {
    return ResponseEntity.ok(regionService.patchRegion(id, name, codeRegion, status));
  }


}
