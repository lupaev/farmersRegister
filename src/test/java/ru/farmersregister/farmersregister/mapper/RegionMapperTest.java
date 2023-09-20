package ru.farmersregister.farmersregister.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.farmersregister.farmersregister.dto.CreateRegionDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegionMapperTest {

  private Region entity;

  private RegionDTO dto;

  private CreateRegionDTO createRegionDTO;


  @Autowired
  private RegionMapper mapper;


  @BeforeEach
  void setUp() {
    entity = new Region();
    entity.setId(1L);
    entity.setName("TestRegion");
    entity.setCodeRegion(11);

    dto = new RegionDTO();
    dto.setId(1L);
    dto.setName("TestRegion");
    dto.setCodeRegion(11);

    createRegionDTO = new CreateRegionDTO();
    createRegionDTO.setName("TestRegion");
    createRegionDTO.setCodeRegion(11);
  }

  @AfterEach
  void afterEach() {
    entity = null;
    dto = null;
    createRegionDTO = null;
  }

  @Test
  public void contextLoads() {
    assertNotNull(mapper);
  }

  @Test
  void toEntityPositive() {
    assertNotNull(dto);
    Region entity1 = mapper.toEntity(dto);
    assertNotNull(entity1);
    assertThat(entity.getId()).isEqualTo(entity1.getId());
    assertThat(entity.getName()).isEqualTo(entity1.getName());
    assertThat(entity.getCodeRegion()).isEqualTo(entity1.getCodeRegion());
  }

  @Test
  void toEntityNegative() {
    assertNotNull(dto);
    assertNotNull(entity);
    Region entity1 = mapper.toEntity(dto);
    assertNotNull(entity1);
    assertThat(entity.getId()).isEqualTo(entity1.getId());
    assertThat(entity.getName()).isEqualTo(entity1.getName());
    assertThat(entity.getCodeRegion()).isEqualTo(entity1.getCodeRegion());
    entity1.setId(3L);
    assertNotEquals(entity, entity1);
  }


  @Test
  void toDTOPositive() {
    assertNotNull(entity);
    RegionDTO dto1 = mapper.toDTO(entity);
    assertNotNull(dto1);
    assertThat(dto.getId()).isEqualTo(dto1.getId());
    assertThat(dto.getName()).isEqualTo(dto1.getName());
    assertThat(dto.getCodeRegion()).isEqualTo(dto1.getCodeRegion());
  }

  @Test
  void toDTONegative() {
    assertNotNull(entity);
    assertNotNull(dto);
    RegionDTO dto1 = mapper.toDTO(entity);
    assertNotNull(dto1);
    assertThat(dto.getId()).isEqualTo(dto1.getId());
    assertThat(dto.getName()).isEqualTo(dto1.getName());
    assertThat(dto.getCodeRegion()).isEqualTo(dto1.getCodeRegion());
    dto1.setCodeRegion(123123);
    assertNotEquals(dto, dto1);
  }

  @Test
  void toDTOListPositive() {
    assertNotNull(entity);
    List<Region> regions = new ArrayList<>();
    regions.add(entity);
    Collection<RegionDTO> dtos = mapper.toDTOList(regions);
    assertNotNull(dtos);
    assertTrue(dtos.contains(mapper.toDTO(entity)));
  }

  @Test
  void toDTOListNegative() {
    assertNotNull(entity);
    Region region = new Region();
    region.setName(entity.getName());
    List<Region> regions = new ArrayList<>();
    regions.add(entity);
    Collection<RegionDTO> dtos = mapper.toDTOList(regions);
    assertNotNull(dtos);
    assertFalse(dtos.contains(mapper.toDTO(region)));
  }

  @Test
  void updateEntityPositive() {
    assertNotNull(entity);
    assertNotNull(createRegionDTO);
    createRegionDTO.setCodeRegion(999);
    mapper.updateEntity(createRegionDTO, entity);
    assertEquals(createRegionDTO.getCodeRegion(), entity.getCodeRegion());
  }

  @Test
  void updateEntityNegative() {
    assertNotNull(entity);
    assertNotNull(createRegionDTO);
    createRegionDTO.setCodeRegion(999);
    mapper.updateEntity(createRegionDTO, entity);
    entity.setCodeRegion(666);
    assertNotEquals(createRegionDTO.getCodeRegion(), entity.getCodeRegion());
  }
}