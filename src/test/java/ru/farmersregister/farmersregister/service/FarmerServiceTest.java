package ru.farmersregister.farmersregister.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.exception.ElemNotFound;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.impl.FarmerServiceImpl;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FarmerServiceTest {

  @InjectMocks
  private FarmerServiceImpl service;

  @Mock
  private FarmerRepository repository;

  @Spy
  private FarmerMapper mapper;

  private Farmer entity;

  private FarmerDTO dto;


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
  }

  @AfterEach
  void afterEach() {
    entity = null;
    dto = null;
  }

  @Test
  public void contextLoads() {
    assertNotNull(service);
  }

  @Test
  void findAllPositive() {
    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
    FarmerMapper mapper = mock(FarmerMapper.class);

    List<Farmer> farmers = new ArrayList<>();
    farmers.add(entity);
    List<FarmerDTO> farmerDTOS = new ArrayList<>();
    farmerDTOS.add(dto);

    when(repository.findAll()).thenReturn(farmers);
    when(mapper.toDTOList(farmers)).thenReturn(farmerDTOS);

    assertThat(repository.findAll()).isNotNull().isEqualTo(farmers);
    assertThat(mapper.toDTOList(farmers)).isNotNull().isEqualTo(farmerDTOS);

    verify(repository, times(1)).findAll();
    verify(mapper, times(1)).toDTOList(farmers);
  }

  @Test
  void findAllNegative() {
    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
    FarmerMapper mapper = mock(FarmerMapper.class);

    List<Farmer> farmers = new ArrayList<>();
    farmers.add(entity);
    List<FarmerDTO> farmerDTOS = new ArrayList<>();
    farmerDTOS.add(dto);

    when(repository.findAll()).thenThrow(ElemNotFound.class);

    assertThrows(ElemNotFound.class, () -> repository.findAll());

    verify(repository, times(1)).findAll();
  }

  @Test
  void addFarmerPositive() {
    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
    FarmerMapper mapper = mock(FarmerMapper.class);

    when(mapper.toEntity(dto)).thenReturn(entity);
    when(service.addFarmer(dto)).thenReturn(dto);
    when(repository.save(any(Farmer.class))).thenReturn(entity);
    assertThat(service.addFarmer(dto)).isNotNull()
        .isEqualTo(dto).isExactlyInstanceOf(FarmerDTO.class);
    assertThat(mapper.toEntity(dto)).isNotNull().isEqualTo(entity)
        .isExactlyInstanceOf(ru.farmersregister.farmersregister.entity.Farmer.class);
    assertThat(repository.save(entity)).isNotNull().isExactlyInstanceOf(Farmer.class);

    verify(repository, times(1)).save(entity);
    verify(service, times(1)).addFarmer(dto);
    verify(mapper, times(1)).toEntity(dto);
  }

  @Test
  void addFarmerNegative() {
    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
    FarmerMapper mapper = mock(FarmerMapper.class);

    when(mapper.toEntity(any())).thenThrow(NullPointerException.class);
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.addFarmer(dto)).thenThrow(RuntimeException.class);
    assertThrows(NullPointerException.class, () -> mapper.toEntity(any()));
    assertThrows(RuntimeException.class, () -> repository.save(any(Farmer.class)));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.addFarmer(dto));

    verify(repository, times(1)).save(any());
    verify(service, times(1)).addFarmer(dto);
    verify(mapper, times(1)).toEntity(any());
  }

  @Test
  void patchFarmerPositive() {
    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
    FarmerMapper mapper = mock(FarmerMapper.class);
    Long id = 1L;

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
    when(mapper.toDTO(entity)).thenReturn(dto);
    doNothing().when(mapper).updateEntity(dto, entity);
    when(repository.save(any(Farmer.class))).thenReturn(entity);
    when(service.patchFarmer(id, dto)).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(entity)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(FarmerDTO.class);
    assertDoesNotThrow(
        () -> mapper.updateEntity(dto, entity));
    assertThat(repository.save(entity)).isNotNull().isExactlyInstanceOf(Farmer.class);

    assertThat(
        service.patchFarmer(id, dto)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(FarmerDTO.class);

    verify(repository, times(1)).save(entity);
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Farmer.class));
    verify(mapper, times(1)).updateEntity(any(FarmerDTO.class), any(Farmer.class));

    verify(service, times(1)).patchFarmer(id, dto);
  }

  @Test
  void patchFarmerNegative() {
    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
    FarmerMapper mapper = mock(FarmerMapper.class);
    Long id = 1L;

    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
    doThrow(NullPointerException.class).when(mapper).updateEntity(any(), any());
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.patchFarmer(id, dto))
        .thenThrow(RuntimeException.class);
    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
    assertThrows(NullPointerException.class, () -> mapper.updateEntity(any(), any()));
    assertThrows(RuntimeException.class, () -> repository.save(any()));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.patchFarmer(id, dto));

    verify(repository, times(1)).save(any());
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any());
    verify(mapper, times(1)).updateEntity(any(), any());

    verify(service, times(1)).patchFarmer(id, dto);
  }

  @Test
  void getFarmerPositive() {
    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
    FarmerMapper mapper = mock(FarmerMapper.class);

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
    when(mapper.toDTO(entity)).thenReturn(dto);
    when(service.getFarmer(anyLong())).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(entity)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(FarmerDTO.class);
    assertThat(service.getFarmer(anyLong())).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(FarmerDTO.class);

    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Farmer.class));
    verify(service, times(1)).getFarmer(anyLong());
  }

  @Test
  void getFarmerNegative() {
    FarmerServiceImpl service = mock(FarmerServiceImpl.class);
    FarmerMapper mapper = mock(FarmerMapper.class);

    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
    when(mapper.toDTO(entity)).thenThrow(NullPointerException.class);
    when(service.getFarmer(anyLong())).thenThrow(ElemNotFound.class);

    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(entity));
    assertThrows(ElemNotFound.class, () -> service.getFarmer(anyLong()));

    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(entity);
    verify(service, times(1)).getFarmer(anyLong());
  }


}