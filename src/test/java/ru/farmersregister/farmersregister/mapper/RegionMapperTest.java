package ru.farmersregister.farmersregister.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.Status;

@SpringBootTest
class RegionMapperTest {

  private Region entity;

  private RegionDTO dto;


  @Autowired
  private RegionMapper mapper;


  @BeforeEach
  void setUp() {
    entity = new Region();
    entity.setId(1L);
    entity.setName("TestRegion");
    entity.setCodeRegion(11);
    entity.setStatus(Status.ACTIVE);

    dto = new RegionDTO();
    dto.setId(1L);
    dto.setName("TestRegion");
    dto.setCodeRegion(11);
    dto.setStatus(Status.ACTIVE);
  }

  @AfterEach
  void afterEach() {
    entity = null;
    dto = null;
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
    assertThat(entity.getStatus()).isEqualTo(entity1.getStatus());
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
    assertThat(entity.getStatus()).isEqualTo(entity1.getStatus());
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
    assertThat(dto.getStatus()).isEqualTo(dto1.getStatus());
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
    assertThat(dto.getStatus()).isEqualTo(dto1.getStatus());
    dto1.setStatus(Status.NONACTIVE);
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
    assertNotNull(dto);
    dto.setCodeRegion(999);
    mapper.updateEntity(dto, entity);
    assertEquals(dto.getCodeRegion(), entity.getCodeRegion());
  }

  @Test
  void updateEntityNegative() {
    assertNotNull(entity);
    assertNotNull(dto);
    dto.setCodeRegion(999);
    mapper.updateEntity(dto, entity);
    entity.setCodeRegion(666);
    assertNotEquals(dto.getCodeRegion(), entity.getCodeRegion());
  }
}