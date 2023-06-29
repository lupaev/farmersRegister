package ru.farmersregister.farmersregister.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FarmerServiceTest {
//
//  @InjectMocks
//  private FarmerServiceImpl service;
//
//  @Mock
//  private FarmerRepository repository;
//
//  @Spy
//  private FarmerMapper mapper;
//
//  @Spy
//  private FarmerFullMapper fullMapper;
//
//  private Farmer entity;
//
//  private FarmerDTO dto;
//
//  private FarmerFullDTO fullDTO;
//
//  @BeforeEach
//  void setUp() {
//
//    Region region = new Region();
//    region.setId(1L);
//    region.setName("TestRegion");
//    region.setCodeRegion(11);
//    region.setStatus(ACTIVE);
//
//    Region regionField = new Region();
//    regionField.setId(3L);
//    regionField.setName("TestRegion3");
//    regionField.setCodeRegion(33);
//    regionField.setStatus(ACTIVE);
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
//    entity.setStatus(ACTIVE);
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
//    dto.setStatus(ACTIVE);
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
//  public void contextLoads() {
//    assertNotNull(service);
//  }
//
//  @Test
//  void findAllPositive() {
//    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
//    FarmerMapper mapper = mock(FarmerMapper.class);
//
//    List<Farmer> farmers = new ArrayList<>();
//    farmers.add(entity);
//    List<FarmerDTO> farmerDTOS = new ArrayList<>();
//    farmerDTOS.add(dto);
//
//    when(repository.findAll()).thenReturn(farmers);
//    when(mapper.toDTOList(farmers)).thenReturn(farmerDTOS);
//    when(service.findAll(SortFarmer.ALL)).thenReturn(farmerDTOS);
//
//    assertThat(repository.findAll()).isNotNull().isEqualTo(farmers);
//    assertThat(mapper.toDTOList(farmers)).isNotNull().isEqualTo(farmerDTOS);
//    assertThat(service.findAll(SortFarmer.ALL)).isNotNull().isEqualTo(farmerDTOS);
//
//    verify(repository, times(1)).findAll();
//    verify(service, times(1)).findAll(SortFarmer.ALL);
//    verify(mapper, times(1)).toDTOList(farmers);
//  }
//
//  @Test
//  void findAllNegative() {
//    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
//    FarmerMapper mapper = mock(FarmerMapper.class);
//
//    List<Farmer> farmers = new ArrayList<>();
//    farmers.add(entity);
//    List<FarmerDTO> farmerDTOS = new ArrayList<>();
//    farmerDTOS.add(dto);
//
//    when(repository.findAll()).thenThrow(ElemNotFound.class);
//    when(service.findAll(SortFarmer.ALL)).thenThrow(RuntimeException.class);
//
//    assertThrows(ElemNotFound.class, () -> repository.findAll());
//    assertThrows(RuntimeException.class, () -> service.findAll(SortFarmer.ALL));
//
//    verify(repository, times(1)).findAll();
//    verify(service, times(1)).findAll(SortFarmer.ALL);
//  }
//
//  @Test
//  void addFarmerPositive() {
//    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
//    FarmerMapper mapper = mock(FarmerMapper.class);
//
//    when(mapper.toEntity(dto)).thenReturn(entity);
//    when(
//        service.addFarmer(entity.getName(), entity.getLegalForm(), entity.getInn(), entity.getKpp(),
//            entity.getOgrn(), entity.getDateRegistration(), entity.getStatus(),
//            entity.getRegion().getId(), 3L)).thenReturn(
//        dto);
//    when(repository.save(any(Farmer.class))).thenReturn(entity);
//    assertThat(
//        service.addFarmer(entity.getName(), entity.getLegalForm(), entity.getInn(), entity.getKpp(),
//            entity.getOgrn(), entity.getDateRegistration(), entity.getStatus(),
//            entity.getRegion().getId(), 3L)).isNotNull()
//        .isEqualTo(dto).isExactlyInstanceOf(FarmerDTO.class);
//    assertThat(mapper.toEntity(dto)).isNotNull().isEqualTo(entity)
//        .isExactlyInstanceOf(ru.farmersregister.farmersregister.entity.Farmer.class);
//    assertThat(repository.save(entity)).isNotNull().isExactlyInstanceOf(Farmer.class);
//
//    verify(repository, times(1)).save(entity);
//    verify(service, times(1)).addFarmer(entity.getName(),
//        entity.getLegalForm(), entity.getInn(), entity.getKpp(), entity.getOgrn(),
//        entity.getDateRegistration(), entity.getStatus(), entity.getRegion().getId(), 3L);
//    verify(mapper, times(1)).toEntity(dto);
//  }
//
//  @Test
//  void addFarmerNegative() {
//    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
//    FarmerMapper mapper = mock(FarmerMapper.class);
//
//    when(mapper.toEntity(any())).thenThrow(NullPointerException.class);
//    when(repository.save(any())).thenThrow(RuntimeException.class);
//    when(
//        service.addFarmer(entity.getName(), entity.getLegalForm(), entity.getInn(), entity.getKpp(),
//            entity.getOgrn(), entity.getDateRegistration(), entity.getStatus(),
//            entity.getRegion().getId(), 3L)).thenThrow(
//        RuntimeException.class);
//    assertThrows(NullPointerException.class, () -> mapper.toEntity(any()));
//    assertThrows(RuntimeException.class, () -> repository.save(any(Farmer.class)));
//    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
//        () -> service.addFarmer(entity.getName(), entity.getLegalForm(), entity.getInn(),
//            entity.getKpp(),
//            entity.getOgrn(), entity.getDateRegistration(), entity.getStatus(),
//            entity.getRegion().getId(), 3L));
//
//    verify(repository, times(1)).save(any());
//    verify(service, times(1)).addFarmer(entity.getName(), entity.getLegalForm(),
//        entity.getInn(), entity.getKpp(), entity.getOgrn(), entity.getDateRegistration(),
//        entity.getStatus(), entity.getRegion().getId(), 3L);
//    verify(mapper, times(1)).toEntity(any());
//  }
//
//  @Test
//  void patchFarmerPositive() {
//    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
//    FarmerMapper mapper = mock(FarmerMapper.class);
//
//    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
//    when(mapper.toDTO(entity)).thenReturn(dto);
//    doNothing().when(mapper).updateEntity(dto, entity);
//    when(repository.save(any(Farmer.class))).thenReturn(entity);
//    when(service.patchFarmer(entity.getId(), entity.getName(), entity.getLegalForm(),
//        entity.getInn(), entity.getKpp(), entity.getOgrn(), entity.getDateRegistration(),
//        entity.getStatus(), entity.getRegion().getId())).thenReturn(dto);
//
//    assertThat(repository.findById(anyLong())).isNotNull();
//    assertThat(mapper.toDTO(entity)).isNotNull().isEqualTo(dto)
//        .isExactlyInstanceOf(FarmerDTO.class);
//    assertDoesNotThrow(
//        () -> mapper.updateEntity(dto, entity));
//    assertThat(repository.save(entity)).isNotNull().isExactlyInstanceOf(Farmer.class);
//
//    assertThat(
//        service.patchFarmer(entity.getId(), entity.getName(), entity.getLegalForm(),
//            entity.getInn(), entity.getKpp(), entity.getOgrn(), entity.getDateRegistration(),
//            entity.getStatus(), entity.getRegion().getId())).isNotNull().isEqualTo(dto)
//        .isExactlyInstanceOf(FarmerDTO.class);
//
//    verify(repository, times(1)).save(entity);
//    verify(repository, times(1)).findById(anyLong());
//    verify(mapper, times(1)).toDTO(any(Farmer.class));
//    verify(mapper, times(1)).updateEntity(any(FarmerDTO.class), any(Farmer.class));
//
//    verify(service, times(1)).patchFarmer(entity.getId(), entity.getName(),
//        entity.getLegalForm(), entity.getInn(), entity.getKpp(), entity.getOgrn(),
//        entity.getDateRegistration(), entity.getStatus(), entity.getRegion().getId());
//  }
//
//  @Test
//  void patchFarmerNegative() {
//    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
//    FarmerMapper mapper = mock(FarmerMapper.class);
//
//    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
//    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
//    doThrow(NullPointerException.class).when(mapper).updateEntity(any(), any());
//    when(repository.save(any())).thenThrow(RuntimeException.class);
//    when(service.patchFarmer(entity.getId(), entity.getName(),
//        entity.getLegalForm(), entity.getInn(), entity.getKpp(), entity.getOgrn(),
//        entity.getDateRegistration(), entity.getStatus(), entity.getRegion().getId()))
//        .thenThrow(RuntimeException.class);
//    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
//    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
//    assertThrows(NullPointerException.class, () -> mapper.updateEntity(any(), any()));
//    assertThrows(RuntimeException.class, () -> repository.save(any()));
//    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
//        () -> service.patchFarmer(entity.getId(), entity.getName(),
//            entity.getLegalForm(), entity.getInn(), entity.getKpp(), entity.getOgrn(),
//            entity.getDateRegistration(), entity.getStatus(), entity.getRegion().getId()));
//
//    verify(repository, times(1)).save(any());
//    verify(repository, times(1)).findById(anyLong());
//    verify(mapper, times(1)).toDTO(any());
//    verify(mapper, times(1)).updateEntity(any(), any());
//
//    verify(service, times(1)).patchFarmer(entity.getId(), entity.getName(),
//        entity.getLegalForm(), entity.getInn(), entity.getKpp(), entity.getOgrn(),
//        entity.getDateRegistration(), entity.getStatus(), entity.getRegion().getId());
//  }
//
//  @Test
//  void getFarmerPositive() {
//    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
//    FarmerFullMapper fullMapper = mock(FarmerFullMapper.class);
//
//    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
//    when(fullMapper.toFullDTO(entity)).thenReturn(fullDTO);
//    when(service.getFarmer(anyLong())).thenReturn(fullDTO);
//
//    assertThat(repository.findById(anyLong())).isNotNull();
//    assertThat(fullMapper.toFullDTO(entity)).isNotNull().isEqualTo(fullDTO)
//        .isExactlyInstanceOf(FarmerFullDTO.class);
//    assertThat(service.getFarmer(anyLong())).isNotNull().isEqualTo(fullDTO)
//        .isExactlyInstanceOf(FarmerFullDTO.class);
//
//    verify(repository, times(1)).findById(anyLong());
//    verify(fullMapper, times(1)).toFullDTO(any(Farmer.class));
//    verify(service, times(1)).getFarmer(anyLong());
//  }
//
//  @Test
//  void getFarmerNegative() {
//    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
//    FarmerFullMapper fullMapper = mock(FarmerFullMapper.class);
//
//    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
//    when(fullMapper.toFullDTO(entity)).thenThrow(NullPointerException.class);
//    when(service.getFarmer(anyLong())).thenThrow(ElemNotFound.class);
//
//    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
//    assertThrows(NullPointerException.class, () -> fullMapper.toFullDTO(entity));
//    assertThrows(ElemNotFound.class, () -> service.getFarmer(anyLong()));
//
//    verify(repository, times(1)).findById(anyLong());
//    verify(fullMapper, times(1)).toFullDTO(entity);
//    verify(service, times(1)).getFarmer(anyLong());
//  }
//
//


}