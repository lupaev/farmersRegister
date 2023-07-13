package ru.farmersregister.farmersregister.controller;



import io.swagger.v3.oas.annotations.tags.Tag;
import java.sql.SQLException;
import java.util.Collection;
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
import ru.farmersregister.farmersregister.dto.RegionDTO;
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


  @GetMapping
  public ResponseEntity<Collection<RegionDTO>> findAll() {
    return ResponseEntity.ok(regionService.findAll());
  }

  @GetMapping(value = "/archived")
  public ResponseEntity<Collection<RegionDTO>> findAllInArchive() {
    return ResponseEntity.ok(regionService.findAllInArchive());
  }


  @PostMapping(value = "/add")
  public ResponseEntity<RegionDTO> addRegion(@RequestBody RegionDTO regionDTO) {
    return ResponseEntity.ok(regionService.addRegion(regionDTO));
  }


  @PatchMapping(value = "/patch/{id}")
  public ResponseEntity<RegionDTO> patchRegion(@PathVariable(name = "id") Long id, @RequestBody RegionDTO regionDTO){
    return ResponseEntity.ok(regionService.patchRegion(id, regionDTO));
  }

  @DeleteMapping(value = "/del/{id}")
  public ResponseEntity<RegionDTO> delRegion(@PathVariable(name = "id") Long id)
      throws SQLException {
    return ResponseEntity.ok(regionService.delRegion(id));
  }


}
