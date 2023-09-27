package ru.farmersregister.farmersregister.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.exception.ElementNotFound;
import ru.farmersregister.farmersregister.mapper.RegionMapper;
import ru.farmersregister.farmersregister.repository.RegionRepository;
import ru.farmersregister.farmersregister.service.impl.RegionServiceImpl;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

  @InjectMocks
  private RegionServiceImpl service;

  @Mock
  private RegionRepository repository;

  @Spy
  private RegionMapper mapper;

  private Region entity;

  private RegionDTO dto;

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
  }

  @AfterEach
  void clearTestData() {
    dto = null;
    entity = null;
  }

  @Test
  void findAllPositive() {
    RegionServiceImpl service = mock(RegionServiceImpl.class);
    RegionMapper mapper = mock(RegionMapper.class);

    List<Region> regions = new ArrayList<>();
    regions.add(entity);
    List<RegionDTO> regionDTOS = new ArrayList<>();
    regionDTOS.add(dto);

    when(repository.findAll()).thenReturn(regions);
    when(mapper.toDTOList(regions)).thenReturn(regionDTOS);

    assertThat(repository.findAll()).isNotNull().isEqualTo(regions);
    assertThat(mapper.toDTOList(regions)).isNotNull().isEqualTo(regionDTOS);

    verify(repository, times(1)).findAll();
    verify(mapper, times(1)).toDTOList(regions);
  }

  @Test
  void findAllNegative() {
    RegionServiceImpl service = mock(RegionServiceImpl.class);

    List<Region> regions = new ArrayList<>();
    regions.add(entity);
    List<RegionDTO> regionDTOS = new ArrayList<>();
    regionDTOS.add(dto);

    when(repository.findAll()).thenThrow(ElementNotFound.class);

    assertThrows(ElementNotFound.class, () -> repository.findAll());

    verify(repository, times(1)).findAll();
  }

  @Test
  void addRegionPositive() {
    RegionServiceImpl service = mock(RegionServiceImpl.class);
    RegionMapper mapper = mock(RegionMapper.class);

    when(mapper.toEntity(dto)).thenReturn(entity);
    when(
        service.addRegion(dto)).thenReturn(dto);
    when(repository.save(any(Region.class))).thenReturn(entity);
    assertThat(service.addRegion(dto)).isNotNull()
        .isEqualTo(dto).isExactlyInstanceOf(RegionDTO.class);
    assertThat(mapper.toEntity(dto)).isNotNull().isEqualTo(entity)
        .isExactlyInstanceOf(ru.farmersregister.farmersregister.entity.Region.class);
    assertThat(repository.save(entity)).isNotNull().isExactlyInstanceOf(Region.class);

    verify(repository, times(1)).save(entity);
    verify(service, times(1)).addRegion(dto);
    verify(mapper, times(1)).toEntity(dto);
  }

  @Test
  void addRegionNegative() {
    RegionServiceImpl service = mock(RegionServiceImpl.class);
    RegionMapper mapper = mock(RegionMapper.class);

    when(mapper.toEntity(any())).thenThrow(NullPointerException.class);
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.addRegion(dto)).thenThrow(
        RuntimeException.class);
    assertThrows(NullPointerException.class, () -> mapper.toEntity(any()));
    assertThrows(RuntimeException.class, () -> repository.save(any(Region.class)));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.addRegion(dto));

    verify(repository, times(1)).save(any());
    verify(service, times(1)).addRegion(dto);
    verify(mapper, times(1)).toEntity(any());
  }

  @Test
  void patchRegionPositive() {
    RegionServiceImpl service = mock(RegionServiceImpl.class);
    RegionMapper mapper = mock(RegionMapper.class);
    Long id = 1L;

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
    when(mapper.toDTO(entity)).thenReturn(dto);
    doNothing().when(mapper).updateEntity(dto, entity);
    when(repository.save(any(Region.class))).thenReturn(entity);
    when(service.patchRegion(id, dto)).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(entity)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(RegionDTO.class);
    assertDoesNotThrow(
        () -> mapper.updateEntity(dto, entity));
    assertThat(repository.save(entity)).isNotNull().isExactlyInstanceOf(Region.class);

    assertThat(
        service.patchRegion(id, dto)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(RegionDTO.class);

    verify(repository, times(1)).save(entity);
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Region.class));
    verify(mapper, times(1)).updateEntity(any(RegionDTO.class), any(Region.class));

    verify(service, times(1)).patchRegion(id, dto);
  }

  @Test
  void patchRegionNegative() {
    RegionServiceImpl service = mock(RegionServiceImpl.class);
    RegionMapper mapper = mock(RegionMapper.class);
    Long id = 1L;

    when(repository.findById(anyLong())).thenThrow(ElementNotFound.class);
    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
    doThrow(NullPointerException.class).when(mapper).updateEntity(any(), any());
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.patchRegion(id, dto)).thenThrow(RuntimeException.class);
    assertThrows(ElementNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
    assertThrows(NullPointerException.class, () -> mapper.updateEntity(any(), any()));
    assertThrows(RuntimeException.class, () -> repository.save(any()));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.patchRegion(id, dto));

    verify(repository, times(1)).save(any());
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any());
    verify(mapper, times(1)).updateEntity(any(), any());

    verify(service, times(1)).patchRegion(id, dto);
  }
}