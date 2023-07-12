package ru.farmersregister.farmersregister.controller;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
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

  private Farmer entity;

  private FarmerDTO dto;

  private FarmerFullDTO fullDTO;


//  @Test
//  public void contextLoads() {
//    assertNotNull(controller);
//  }
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
//    dto = new FarmerDTO();
//    dto.setId(1L);
//    dto.setName("TestName");
//    dto.setLegalForm(LegalForm.FL);
//    dto.setInn(123456);
//    dto.setKpp(654321);
//    dto.setOgrn(654789);
//    dto.setStatus(Status.ACTIVE);
//    dto.setRegistrationRegion(region.getId());
//    dto.setDateRegistration(LocalDate.parse("2013-12-20"));
//    dto.setFields(fields);
//
//    fullDTO = new FarmerFullDTO();
//    fullDTO.setId(1L);
//    fullDTO.setName("TestName");
//    fullDTO.setLegalForm(LegalForm.FL);
//    fullDTO.setInn(123456);
//    fullDTO.setKpp(654321);
//    fullDTO.setOgrn(654789);
//    fullDTO.setRegistrationRegionName(region.getName());
//    fullDTO.setDateRegistration(LocalDate.parse("2013-12-20"));
//    fullDTO.setStatus(ACTIVE);
//    fullDTO.setFields(fields);
//  }
//
//  @AfterEach
//  void afterEach() {
//    entity = null;
//    dto = null;
//    fullDTO = null;
//  }
//
//  @Test
//  void findAll() throws Exception {
//    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    String url = "/farmer";
//
//    List<Farmer> farmers = new ArrayList<>();
//    farmers.add(entity);
//    List<FarmerDTO> farmerDTOS = new ArrayList<>();
//    farmerDTOS.add(dto);
//
//    when(service.findAll(SortFarmer.ALL)).thenReturn(farmerDTOS);
//
//    mockMvc.perform(get(url)
//            .param("sort", "ALL")
//            .contentType(MediaType.APPLICATION_JSON_VALUE)
//            .accept(MediaType.APPLICATION_JSON))
//        .andDo(print())
//        .andExpect(status().isOk());
//  }
//
//  @Test
//  void getFarmer() throws Exception {
//    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    String url = "/farmer/1";
//
//
//    when(service.getFarmer(anyLong())).thenReturn(fullDTO);
//    mockMvc.perform(get(url)
//            .contentType(MediaType.APPLICATION_JSON_VALUE)
//            .accept(MediaType.APPLICATION_JSON))
//        .andDo(print())
//        .andExpect(status().isOk());
//  }
//
//  @Test
//  void add() throws Exception {
//    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    String url = "/farmer/add";
//
//    when(
//        service.addFarmer(entity.getName(), entity.getLegalForm(), entity.getInn(), entity.getKpp(),
//            entity.getOgrn(), entity.getDateRegistration(), entity.getStatus(),
//            entity.getRegion().getId(), 3L)).thenReturn(
//        dto);
//    when(repository.save(entity)).thenReturn(entity);
//    mockMvc.perform(post(url)
//            .param("name", "TestName")
//            .param("legal form", "FL")
//            .param("inn", "123456")
//            .param("kpp", "654321")
//            .param("ogrn", "654789")
//            .param("date registration", "2013-12-20")
//            .param("status", "ACTIVE")
//            .param("registration region", "1")
//            .param("region_id", "3")
//            .contentType(MediaType.APPLICATION_JSON_VALUE)
//            .accept(MediaType.APPLICATION_JSON))
//        .andDo(print())
//        .andExpect(status().isOk());
//  }
//
//  @Test
//  void patchFarmer() throws Exception {
//    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    String url1 = "/farmer/patch/{id}";
//    Long id = 1L;
//
//    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
//    when(repository.save(any(Farmer.class))).thenReturn(entity);
//    when(service.patchFarmer(entity.getId(), entity.getName(), entity.getLegalForm(),
//        entity.getInn(), entity.getKpp(), entity.getOgrn(), entity.getDateRegistration(),
//        entity.getStatus(), entity.getRegion().getId())).thenReturn(dto);
//
//    mockMvc.perform(MockMvcRequestBuilders
//            .patch(url1, id)
//            .param("name", "TestName")
//            .param("legal form", "FL")
//            .param("inn", "123456")
//            .param("kpp", "654321")
//            .param("ogrn", "654789")
//            .param("status", "ACTIVE")
//            .param("registration region", "1")
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON))
//        .andDo(print())
//        .andExpect(status().isOk());
//
//  }


}