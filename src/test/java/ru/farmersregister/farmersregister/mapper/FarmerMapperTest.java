package ru.farmersregister.farmersregister.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.Status;

@SpringBootTest
class FarmerMapperTest {
  private Farmer entity;

  private FarmerDTO dto;

  @Autowired
  private FarmerMapper mapper;

  @BeforeEach
  void setUp() {

    Region region = new Region();
    region.setId(1L);
    region.setName("TestRegion");
    region.setCodeRegion(11);
    region.setStatus(Status.ACTIVE);

    Region regionField = new Region();
    regionField.setId(3L);
    regionField.setName("TestRegion3");
    regionField.setCodeRegion(33);
    regionField.setStatus(Status.ACTIVE);

    Collection<Region> regions = new ArrayList<>();
    regions.add(regionField);

    Collection<Long> fields = new ArrayList<>();
    fields.add(regionField.getId());

    entity = new Farmer();
    entity.setId(1L);
    entity.setName("TestName");
    entity.setLegalForm(LegalForm.FL);
    entity.setInn(123456);
    entity.setKpp(654321);
    entity.setStatus(Status.ACTIVE);
    entity.setRegion(region);
    entity.setOgrn(654789);
    entity.setDateRegistration(LocalDate.parse("2013-12-20"));
    entity.setRegions(regions);

    dto = new FarmerDTO();
    dto.setId(1L);
    dto.setName("TestName");
    dto.setLegalForm(LegalForm.FL);
    dto.setInn(123456);
    dto.setKpp(654321);
    dto.setOgrn(654789);
    dto.setStatus(Status.ACTIVE);
    dto.setRegistrationRegion(region.getId());
    dto.setDateRegistration(LocalDate.parse("2013-12-20"));
    dto.setFields(fields);
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
    Farmer entity1 = mapper.toEntity(dto);
    assertNotNull(entity1);
    assertThat(entity.getId()).isEqualTo(entity1.getId());
    assertThat(entity.getName()).isEqualTo(entity1.getName());
    assertThat(entity.getLegalForm()).isEqualTo(entity1.getLegalForm());
    assertThat(entity.getInn()).isEqualTo(entity1.getInn());
    assertThat(entity.getKpp()).isEqualTo(entity1.getKpp());
    assertThat(entity.getOgrn()).isEqualTo(entity1.getOgrn());
    assertThat(entity.getDateRegistration()).isEqualTo(entity1.getDateRegistration());
    assertThat(entity.getStatus()).isEqualTo(entity1.getStatus());
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
    assertThat(entity.getStatus()).isEqualTo(entity1.getStatus());
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
    assertThat(entity.getStatus()).isEqualTo(dto1.getStatus());
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
    assertThat(entity.getStatus()).isEqualTo(dto1.getStatus());
    dto1.setStatus(Status.NONACTIVE);
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
    assertNotNull(dto);
    dto.setName("RegionTest");
    mapper.updateEntity(dto, entity);
    assertEquals(dto.getName(), entity.getName());
  }

  @Test
  void updateEntityNegative() {
    assertNotNull(entity);
    assertNotNull(dto);
    dto.setName("RegionTest");
    mapper.updateEntity(dto, entity);
    entity.setName("RegionTest1");
    assertNotEquals(dto.getName(), entity.getName());
  }
}