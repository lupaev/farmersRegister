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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.farmersregister.farmersregister.dto.CreateRegionDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.RegionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private CreateRegionDTO createRegionDTO;

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

        dto = new RegionDTO();
        dto.setId(1L);
        dto.setName("TestRegion");
        dto.setCodeRegion(11);

        createRegionDTO = new CreateRegionDTO();
        createRegionDTO.setName("TestRegion");
        createRegionDTO.setCodeRegion(11);
    }

    @AfterEach
    void clearTestData() {
        dto = null;
        entity = null;
    }


    @Test
    void addRegion() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url = "/region/add";

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonEntity = mapper.writeValueAsString(entity);

        when(service.addRegion(createRegionDTO)).thenReturn(dto);
        when(repository.save(any(Region.class))).thenReturn(entity);
        mockMvc.perform(post(url)
                        .content(jsonEntity)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("TestRegion")))
                .andExpect(jsonPath("$.codeRegion", Matchers.is(11)));
    }

    @Test
    void patchRegion() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url1 = "/region/patch/{id}";
        Long id = 1L;

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonEntity = mapper.writeValueAsString(entity);

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(service.patchRegion(id, createRegionDTO)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch(url1, id)
                        .content(jsonEntity)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("TestRegion")))
                .andExpect(jsonPath("$.codeRegion", Matchers.is(11)));
    }
}