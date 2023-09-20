package ru.farmersregister.farmersregister.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.mapper.FarmerMapperImpl;
import ru.farmersregister.farmersregister.mapper.RegionMapperImpl;
import ru.farmersregister.farmersregister.mapper.RegionToLong;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.FarmerInArchiveService;
import ru.farmersregister.farmersregister.service.FarmerService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private RegionRepository regionRepository;

    @MockBean
    private FarmerRepository repository;

    @MockBean
    private FarmerInArchiveService farmerInArchiveService;

    @SpyBean
    private FarmerMapperImpl farmerMapper;

    @SpyBean
    private RegionMapperImpl regionMapper;

    @MockBean
    private RegionToLong regionToLong;

    private Farmer entity;

    private FarmerDTO dto;

    private CreateFarmerDTO createFarmerDTO;


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
        region2.setId(2L);
        region2.setName("TestRegion2");
        region2.setCodeRegion(22);

        Region region3 = new Region();
        region3.setId(3L);
        region3.setName("TestRegion3");
        region3.setCodeRegion(33);

        Collection<Region> regions = new ArrayList<>();
        regions.add(region2);
        regions.add(region3);

        Long[] arrLongs = new Long[]{2L, 3L};

        List<Long> ids = new ArrayList<>();
        ids.addAll(Arrays.asList(arrLongs));

        entity = new Farmer();
        entity.setId(1L);
        entity.setName("TestName");
        entity.setInn("123456789012");
        entity.setKpp("123456789012");
        entity.setRegion(region);
        entity.setOgrn("1234567891234");
        entity.setDateRegistration(LocalDate.parse("2013-12-20"));
        entity.setFields(regions);
        entity.setLegalForm("OOO");

        dto = new FarmerDTO();
        dto.setId(1L);
        dto.setName("TestName");
        dto.setInn("123456789012");
        dto.setKpp("123456789012");
        dto.setOgrn("1234567891234");
        dto.setRegistrationRegion(region.getId());
        dto.setDateRegistration(LocalDate.parse("2013-12-20"));
        dto.setRegionIds(ids);
        dto.setLegalForm("OOO");

        createFarmerDTO = new CreateFarmerDTO();
        createFarmerDTO.setName("TestName");
        createFarmerDTO.setInn("123456789012");
        createFarmerDTO.setKpp("123456789");
        createFarmerDTO.setOgrn("1234567891234");
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
    void getFarmer() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url = "/farmer/{id}";
        Long id = 1L;

        when(service.getFarmer(id)).thenReturn(dto);
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
        String jsonEntity = mapper.writeValueAsString(createFarmerDTO);

        when(service.addFarmer(createFarmerDTO)).thenReturn(dto);
        when(farmerMapper.createEntity(any(CreateFarmerDTO.class))).thenReturn(any(Farmer.class));
        when(repository.save(entity)).thenReturn(entity);
        doReturn(dto).when(farmerMapper).toDTO(entity);
        mockMvc.perform(post(url)
                        .content(String.valueOf(jsonEntity))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", Matchers.is(dto.getName())))
        .andExpect(jsonPath("$.inn", Matchers.is(dto.getInn())))
        .andExpect(jsonPath("$.ogrn", Matchers.is(dto.getOgrn())))
        .andExpect(jsonPath("$.kpp", Matchers.is(dto.getKpp())));
    }

    @Test
    void patchFarmer() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url = "/farmer/patch/{id}";
        Long id = 1L;

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonEntity = mapper.writeValueAsString(createFarmerDTO);

        when(repository.findById(entity.getId())).thenReturn(Optional.ofNullable(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(service.patchFarmer(id, createFarmerDTO)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch(url, id)
                        .content(jsonEntity)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", Matchers.is(dto.getName())))
        .andExpect(jsonPath("$.inn", Matchers.is(dto.getInn())))
        .andExpect(jsonPath("$.ogrn", Matchers.is(dto.getOgrn())))
        .andExpect(jsonPath("$.kpp", Matchers.is(dto.getKpp())));

    }


    @Test
    void delRegion() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url1 = "/farmer/del/{id}";
        Long id = 1L;
        when(repository.findById(entity.getId())).thenReturn(Optional.ofNullable(entity));
        doNothing().when(repository).deleteAllById(Collections.singleton(id));
        when(service.delFarmer(id)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(url1, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", Matchers.is(dto.getName())))
        .andExpect(jsonPath("$.inn", Matchers.is(dto.getInn())))
        .andExpect(jsonPath("$.ogrn", Matchers.is(dto.getOgrn())))
        .andExpect(jsonPath("$.kpp", Matchers.is(dto.getKpp())));
    }
}