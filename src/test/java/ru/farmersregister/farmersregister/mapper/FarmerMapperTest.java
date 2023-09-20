package ru.farmersregister.farmersregister.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FarmerMapperTest {

  private Farmer entity;

  private FarmerDTO dto;

  private CreateFarmerDTO createFarmerDTO;

  @Autowired
  private FarmerMapper mapper;

  @BeforeEach
  void setUp() {

    Region region = new Region();
    region.setId(1L);
    region.setName("TestRegion");
    region.setCodeRegion(11);

    Region region2 = new Region();
    region2.setId(2L);
    region2.setName("TestRegion2");
    region2.setCodeRegion(22);

    Region region3 = new Region();
    region3.setId(3L);
    region3.setName("TestRegion3");
    region3.setCodeRegion(33);

    List<Region> regions = new ArrayList<>();
    regions.add(region2);
    regions.add(region3);

    Long[] arrLongs = new Long[]{1L, 2L, 3L};

    List<Long> ids = new ArrayList<>();
    ids.addAll(Arrays.asList(arrLongs));

    entity = new Farmer();
    entity.setId(1L);
    entity.setName("TestName");
    entity.setInn("123456");
    entity.setKpp("654321");
    entity.setRegion(region);
    entity.setOgrn("654789");
    entity.setDateRegistration(LocalDate.parse("2013-12-20"));
    entity.setFields(regions);
    entity.setLegalForm("OOO");

    dto = new FarmerDTO();
    dto.setId(1L);
    dto.setName("TestName");
    dto.setInn("123456");
    dto.setKpp("654321");
    dto.setOgrn("654789");
    dto.setRegistrationRegion(region.getId());
    dto.setDateRegistration(LocalDate.parse("2013-12-20"));
    dto.setRegionIds(ids);
    dto.setLegalForm("OOO");

    createFarmerDTO = new CreateFarmerDTO();
    createFarmerDTO.setName("TestName");
    createFarmerDTO.setInn("123456");
    createFarmerDTO.setKpp("654321");
    createFarmerDTO.setOgrn("654789");
    createFarmerDTO.setRegistrationRegion(region.getId());
    createFarmerDTO.setDateRegistration(LocalDate.parse("2013-12-20"));
    createFarmerDTO.setRegionIds(ids);
    createFarmerDTO.setLegalForm("OOO");
  }

  @AfterEach
  void afterEach() {
    entity = null;
    dto = null;
    createFarmerDTO = null;
  }

  @Test
  public void contextLoads() {
    assertNotNull(mapper);
  }

  @Test
  void toEntityPositive() {
    assertNotNull(dto);
    Farmer entity1 = mapper.toEntity(dto);
    assertNotNull(entity1);
    assertThat(entity.getId()).isEqualTo(entity1.getId());
    assertThat(entity.getName()).isEqualTo(entity1.getName());
    assertThat(entity.getLegalForm()).isEqualTo(entity1.getLegalForm());
    assertThat(entity.getInn()).isEqualTo(entity1.getInn());
    assertThat(entity.getKpp()).isEqualTo(entity1.getKpp());
    assertThat(entity.getOgrn()).isEqualTo(entity1.getOgrn());
    assertThat(entity.getDateRegistration()).isEqualTo(entity1.getDateRegistration());
  }

  @Test
  void toEntityNegative() {
    assertNotNull(dto);
    Farmer entity1 = mapper.toEntity(dto);
    assertNotNull(entity1);
    assertThat(entity.getId()).isEqualTo(entity1.getId());
    assertThat(entity.getName()).isEqualTo(entity1.getName());
    assertThat(entity.getLegalForm()).isEqualTo(entity1.getLegalForm());
    assertThat(entity.getInn()).isEqualTo(entity1.getInn());
    assertThat(entity.getKpp()).isEqualTo(entity1.getKpp());
    assertThat(entity.getOgrn()).isEqualTo(entity1.getOgrn());
    assertThat(entity.getDateRegistration()).isEqualTo(entity1.getDateRegistration());
    entity1.setId(3L);
    assertNotEquals(entity, entity1);
  }

  @Test
  void toDTOPositive() {
    assertNotNull(entity);
    FarmerDTO dto1 = mapper.toDTO(entity);
    assertNotNull(dto1);
    assertThat(entity.getId()).isEqualTo(dto1.getId());
    assertThat(entity.getName()).isEqualTo(dto1.getName());
    assertThat(entity.getLegalForm()).isEqualTo(dto1.getLegalForm());
    assertThat(entity.getInn()).isEqualTo(dto1.getInn());
    assertThat(entity.getKpp()).isEqualTo(dto1.getKpp());
    assertThat(entity.getOgrn()).isEqualTo(dto1.getOgrn());
    assertThat(entity.getDateRegistration()).isEqualTo(dto1.getDateRegistration());
  }

  @Test
  void toDTONegative() {
    assertNotNull(entity);
    assertNotNull(dto);
    FarmerDTO dto1 = mapper.toDTO(entity);
    assertNotNull(dto1);
    assertThat(entity.getId()).isEqualTo(dto1.getId());
    assertThat(entity.getName()).isEqualTo(dto1.getName());
    assertThat(entity.getLegalForm()).isEqualTo(dto1.getLegalForm());
    assertThat(entity.getInn()).isEqualTo(dto1.getInn());
    assertThat(entity.getKpp()).isEqualTo(dto1.getKpp());
    assertThat(entity.getOgrn()).isEqualTo(dto1.getOgrn());
    assertThat(entity.getDateRegistration()).isEqualTo(dto1.getDateRegistration());
    dto1.setId(3L);
    assertNotEquals(dto, dto1);
  }

  @Test
  void toDTOListPositive() {
    assertNotNull(entity);
    List<Farmer> farmers = new ArrayList<>();
    farmers.add(entity);
    Collection<FarmerDTO> dtos = mapper.toDTOList(farmers);
    assertNotNull(dtos);
    assertTrue(dtos.contains(mapper.toDTO(entity)));
  }

  @Test
  void toDTOListNegative() {
    assertNotNull(entity);
    dto.setName("NON");
    List<Farmer> farmers = new ArrayList<>();
    farmers.add(entity);
    Collection<FarmerDTO> dtos = mapper.toDTOList(farmers);
    assertNotNull(dtos);
    assertFalse(dtos.contains(dto));
  }

  @Test
  void updateEntityPositive() {
    assertNotNull(entity);
    assertNotNull(createFarmerDTO);
    createFarmerDTO.setName("TestName_");
    createFarmerDTO.setOgrn("111111111");
    mapper.updateEntity(createFarmerDTO, entity);
    assertEquals(createFarmerDTO.getName(), entity.getName());
    assertEquals(createFarmerDTO.getOgrn(), entity.getOgrn());
  }

  @Test
  void updateEntityNegative() {
    assertNotNull(entity);
    assertNotNull(createFarmerDTO);
    createFarmerDTO.setName("RegionTest");
    mapper.updateEntity(createFarmerDTO, entity);
    entity.setName("RegionTest1");
    assertNotEquals(createFarmerDTO.getName(), entity.getName());
  }
}