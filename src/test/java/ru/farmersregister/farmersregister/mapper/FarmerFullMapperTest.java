package ru.farmersregister.farmersregister.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.Status;

@SpringBootTest
class FarmerFullMapperTest {

//  private Farmer entity;
//
//  private FarmerFullDTO dto;
//
//  @Autowired
//  private FarmerFullMapper mapper;
//
//  @BeforeEach
//  void setUp() {
//
//    Region region = new Region();
//    region.setId(1L);
//    region.setName("TestRegion");
//    region.setCodeRegion(11);
//    region.setStatus(Status.ACTIVE);
//
//    Region regionField = new Region();
//    regionField.setId(3L);
//    regionField.setName("TestRegion3");
//    regionField.setCodeRegion(33);
//    regionField.setStatus(Status.ACTIVE);
//
//    Collection<Region> regions = new ArrayList<>();
//    regions.add(regionField);
//
//    Collection<Long> fields = new ArrayList<>();
//    fields.add(regionField.getId());
//
//    entity = new Farmer();
//    entity.setId(1L);
//    entity.setName("TestName");
//    entity.setLegalForm(LegalForm.FL);
//    entity.setInn(123456);
//    entity.setKpp(654321);
//    entity.setStatus(Status.ACTIVE);
//    entity.setRegion(region);
//    entity.setOgrn(654789);
//    entity.setDateRegistration(LocalDate.parse("2013-12-20"));
//    entity.setRegions(regions);
//
//    dto = new FarmerFullDTO();
//    dto.setId(1L);
//    dto.setName("TestName");
//    dto.setLegalForm(LegalForm.FL);
//    dto.setInn(123456);
//    dto.setKpp(654321);
//    dto.setOgrn(654789);
//    dto.setStatus(Status.ACTIVE);
//    dto.setRegistrationRegionName(region.getName());
//    dto.setDateRegistration(LocalDate.parse("2013-12-20"));
//    dto.setFields(fields);
//  }
//
//  @AfterEach
//  void afterEach() {
//    entity = null;
//    dto = null;
//  }
//
//  @Test
//  public void contextLoads() {
//    assertNotNull(mapper);
//  }
//
//  @Test
//  void toFullDTOPositive() {
//    assertNotNull(entity);
//    FarmerFullDTO dto1 = mapper.toFullDTO(entity);
//    assertNotNull(dto1);
//    assertEquals(dto1, dto);
//    assertEquals(dto1.getRegistrationRegionName(), dto.getRegistrationRegionName());
//  }
//
//  @Test
//  void toFullDTONegative() {
//    assertNotNull(entity);
//    FarmerFullDTO dto1 = mapper.toFullDTO(entity);
//    assertNotNull(dto1);
//    dto1.setStatus(Status.NONACTIVE);
//    assertNotEquals(dto1, dto);
//  }


}