package ru.farmersregister.farmersregister.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.mapper.FarmerMapperImpl;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.FarmerService;

@WebMvcTest(FarmerController.class)
class FarmerControllerTest {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private MockMvc mockMvc;
  @InjectMocks
  private FarmerController controller;

  @MockBean
  private FarmerService service;

  @MockBean
  private FarmerRepository repository;

  @SpyBean
  private FarmerMapperImpl farmerMapper;

  private Farmer entity;

  private FarmerDTO dto;


  @Test
  public void contextLoads() {
    assertNotNull(controller);
  }

  @BeforeEach
  void setUp() {

    Region region = new Region();
    region.setId(1L);
    region.setName("TestRegion");
    region.setCodeRegion(11);

    Region region2 = new Region();
    region.setId(2L);
    region.setName("TestRegion2");
    region.setCodeRegion(22);

    Region region3 = new Region();
    region3.setId(3L);
    region3.setName("TestRegion3");
    region3.setCodeRegion(33);

    Collection<Region> regions = new ArrayList<>();
    regions.add(region2);
    regions.add(region3);

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
    dto.setRegion(region);
    dto.setDateRegistration(LocalDate.parse("2013-12-20"));
    dto.setFields(regions);
    dto.setLegalForm("OOO");

  }

  @AfterEach
  void afterEach() {
    entity = null;
    dto = null;
  }

  @Test
  void getFarmer() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/farmer/{id}";
    Long id = 1L;

    when(service.getFarmer(anyLong())).thenReturn(dto);
    when(repository.findById(id)).thenReturn(Optional.ofNullable(entity));
    mockMvc.perform(get(url, id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(1)))
        .andExpect(jsonPath("$.name", Matchers.is(dto.getName())))
        .andExpect(jsonPath("$.inn", Matchers.is(dto.getInn())))
        .andExpect(jsonPath("$.ogrn", Matchers.is(dto.getOgrn())))
        .andExpect(jsonPath("$.kpp", Matchers.is(dto.getKpp())));
  }

  @Test
  void addFarmer() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/farmer/add";

    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    String jsonEntity = mapper.writeValueAsString(entity);

    when(service.addFarmer(dto)).thenReturn(dto);
    when(repository.save(entity)).thenReturn(entity);
    when(farmerMapper.toDTO(entity)).thenReturn(dto);
    mockMvc.perform(post(url)
            .content(jsonEntity)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(1)))
        .andExpect(jsonPath("$.name", Matchers.is(dto.getName())))
        .andExpect(jsonPath("$.inn", Matchers.is(dto.getInn())))
        .andExpect(jsonPath("$.ogrn", Matchers.is(dto.getOgrn())))
        .andExpect(jsonPath("$.region.id", Matchers.is(2)))
        .andExpect(jsonPath("$.kpp", Matchers.is(dto.getKpp())));
  }

  @Test
  void patchFarmer() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/farmer/patch/{id}";
    Long id = 1L;

    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    String jsonEntity = mapper.writeValueAsString(entity);

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
    when(repository.save(any(Farmer.class))).thenReturn(entity);
    when(service.patchFarmer(id, dto)).thenReturn(dto);

    mockMvc.perform(MockMvcRequestBuilders
            .patch(url, id)
            .content(jsonEntity)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(1)))
        .andExpect(jsonPath("$.name", Matchers.is(dto.getName())))
        .andExpect(jsonPath("$.inn", Matchers.is(dto.getInn())))
        .andExpect(jsonPath("$.ogrn", Matchers.is(dto.getOgrn())))
        .andExpect(jsonPath("$.region.id", Matchers.is(2)))
        .andExpect(jsonPath("$.kpp", Matchers.is(dto.getKpp())));

  }


  @Test
  void delRegion() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url1 = "/farmer/del/{id}";
    Long id = 1L;
    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
    doNothing().when(repository).deleteAllById(Collections.singleton(id));
    when(service.delFarmer(id)).thenReturn(dto);

    mockMvc.perform(MockMvcRequestBuilders
            .delete(url1, id)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(1)))
        .andExpect(jsonPath("$.name", Matchers.is(dto.getName())))
        .andExpect(jsonPath("$.inn", Matchers.is(dto.getInn())))
        .andExpect(jsonPath("$.ogrn", Matchers.is(dto.getOgrn())))
        .andExpect(jsonPath("$.region.id", Matchers.is(2)))
        .andExpect(jsonPath("$.kpp", Matchers.is(dto.getKpp())));
  }
}