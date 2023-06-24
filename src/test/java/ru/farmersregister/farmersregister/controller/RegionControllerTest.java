package ru.farmersregister.farmersregister.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerFullDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.SortFarmer;
import ru.farmersregister.farmersregister.entity.SortRegion;
import ru.farmersregister.farmersregister.entity.Status;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.FarmerService;
import ru.farmersregister.farmersregister.service.RegionService;

@WebMvcTest(RegionController.class)
class RegionControllerTest {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private MockMvc mockMvc;
  @InjectMocks
  private RegionController controller;

  @MockBean
  private RegionService service;

  @MockBean
  private RegionRepository repository;

  private Region entity;

  private RegionDTO dto;

//  private FarmerFullDTO fullDTO;

  @Test
  public void contextLoads() {
    assertNotNull(controller);
  }

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
  void clearTestData() {
    dto = null;
    entity = null;
  }

  @Test
  void findAll() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/region";

    List<Region> regions = new ArrayList<>();
    regions.add(entity);
    List<RegionDTO> regionDTOS = new ArrayList<>();
    regionDTOS.add(dto);

    when(repository.findAll()).thenReturn(regions);
    when(service.findAll(SortRegion.ALL)).thenReturn(regionDTOS);

    mockMvc.perform(get(url)
            .param("sort by", "ALL")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void addRegion() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/region/add";

    when(
        service.addRegion(entity.getName(), entity.getCodeRegion(), entity.getStatus()))
        .thenReturn(dto);
    when(repository.save(any(Region.class))).thenReturn(entity);
    mockMvc.perform(post(url)
            .param("name", "TestRegion")
            .param("code", "11")
            .param("status", "ACTIVE")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void patchRegion() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url1 = "/region/patch/{id}";
    Long id = 1L;

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
    when(repository.save(any(Region.class))).thenReturn(entity);
    when(service.patchRegion(entity.getId(), entity.getName(), entity.getCodeRegion(),
        entity.getStatus())).thenReturn(dto);

    mockMvc.perform(MockMvcRequestBuilders
            .patch(url1, id)
            .param("name", "TestRegion")
            .param("code", "11")
            .param("status", "ACTIVE")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}