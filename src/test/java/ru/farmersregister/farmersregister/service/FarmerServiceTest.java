package ru.farmersregister.farmersregister.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.farmersregister.farmersregister.dto.CreateFarmerDTO;
import ru.farmersregister.farmersregister.dto.FarmerDTO;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.exception.ElementNotFound;
import ru.farmersregister.farmersregister.mapper.FarmerMapper;
import ru.farmersregister.farmersregister.repository.FarmerRepository;
import ru.farmersregister.farmersregister.service.impl.FarmerServiceImpl;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FarmerServiceTest {

    @InjectMocks
    private FarmerServiceImpl service;

    @Mock
    private FarmerRepository repository;

    @Spy
    private FarmerMapper mapper;

    private Farmer entity;

    private FarmerDTO dto;

    private CreateFarmerDTO createFarmerDTO;


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

        createFarmerDTO = new CreateFarmerDTO();
        createFarmerDTO.setName("TestName");
        createFarmerDTO.setInn("123456");
        createFarmerDTO.setKpp("654321");
        createFarmerDTO.setOgrn("654789");
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
    public void contextLoads() {
        assertNotNull(service);
    }

    @Test
    void findAllPositive() {
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
        List<Farmer> farmers = new ArrayList<>();
        farmers.add(entity);
        List<FarmerDTO> farmerDTOS = new ArrayList<>();
        farmerDTOS.add(dto);

        when(repository.findAll()).thenThrow(ElementNotFound.class);

        assertThrows(ElementNotFound.class, () -> repository.findAll());

        verify(repository, times(1)).findAll();
    }

    @Test
    void addFarmerPositive() {
        FarmerServiceImpl service = mock(FarmerServiceImpl.class);
        FarmerMapper mapper = mock(FarmerMapper.class);

        when(mapper.createEntity(createFarmerDTO)).thenReturn(entity);
        when(service.addFarmer(createFarmerDTO)).thenReturn(dto);
        when(repository.save(entity)).thenReturn(entity);
        assertThat(service.addFarmer(createFarmerDTO)).isNotNull()
                .isEqualTo(dto).isExactlyInstanceOf(FarmerDTO.class);
        assertThat(mapper.createEntity(createFarmerDTO)).isNotNull().isEqualTo(entity)
                .isExactlyInstanceOf(Farmer.class);
        assertThat(repository.save(entity)).isNotNull().isExactlyInstanceOf(Farmer.class);

        verify(repository, times(1)).save(entity);
        verify(service, times(1)).addFarmer(createFarmerDTO);
        verify(mapper, times(1)).createEntity(createFarmerDTO);
    }

    @Test
    void addFarmerNegative() {
        FarmerServiceImpl service = mock(FarmerServiceImpl.class);
        FarmerMapper mapper = mock(FarmerMapper.class);

        when(mapper.createEntity(createFarmerDTO)).thenThrow(NullPointerException.class);
        when(repository.save(entity)).thenThrow(RuntimeException.class);
        when(service.addFarmer(createFarmerDTO)).thenThrow(RuntimeException.class);
        assertThrows(NullPointerException.class, () -> mapper.createEntity(createFarmerDTO));
        assertThrows(RuntimeException.class, () -> repository.save(entity));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                () -> service.addFarmer(createFarmerDTO));

        verify(repository, times(1)).save(entity);
        verify(service, times(1)).addFarmer(createFarmerDTO);
        verify(mapper, times(1)).createEntity(createFarmerDTO);
    }

    @Test
    void patchFarmerPositive() {
        FarmerServiceImpl service = mock(FarmerServiceImpl.class);
        FarmerMapper mapper = mock(FarmerMapper.class);
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.ofNullable(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);
        doNothing().when(mapper).updateEntity(createFarmerDTO, entity);
        when(repository.save(entity)).thenReturn(entity);
        when(service.patchFarmer(id, createFarmerDTO)).thenReturn(dto);

        assertThat(repository.findById(id)).isNotNull();
        assertThat(mapper.toDTO(entity)).isNotNull().isEqualTo(dto)
                .isExactlyInstanceOf(FarmerDTO.class);
        assertDoesNotThrow(
                () -> mapper.updateEntity(createFarmerDTO, entity));
        assertThat(repository.save(entity)).isNotNull().isExactlyInstanceOf(Farmer.class);

        assertThat(
                service.patchFarmer(id, createFarmerDTO)).isNotNull().isEqualTo(dto)
                .isExactlyInstanceOf(FarmerDTO.class);

        verify(repository, times(1)).save(entity);
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).toDTO(any(Farmer.class));
        verify(mapper, times(1)).updateEntity(createFarmerDTO, entity);

        verify(service, times(1)).patchFarmer(id, createFarmerDTO);
    }

    @Test
    void patchFarmerNegative() {
        FarmerServiceImpl service = mock(FarmerServiceImpl.class);
        FarmerMapper mapper = mock(FarmerMapper.class);
        Long id = 1L;

        when(repository.findById(anyLong())).thenThrow(ElementNotFound.class);
        when(mapper.toDTO(entity)).thenThrow(NullPointerException.class);
        doThrow(NullPointerException.class).when(mapper).updateEntity(any(), any());
        when(repository.save(any())).thenThrow(RuntimeException.class);
        when(service.patchFarmer(id, createFarmerDTO))
                .thenThrow(RuntimeException.class);
        assertThrows(ElementNotFound.class, () -> repository.findById(anyLong()));
        assertThrows(NullPointerException.class, () -> mapper.toDTO(entity));
        assertThrows(NullPointerException.class, () -> mapper.updateEntity(any(), any()));
        assertThrows(RuntimeException.class, () -> repository.save(any()));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                () -> service.patchFarmer(id, createFarmerDTO));

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).findById(anyLong());
        verify(mapper, times(1)).toDTO(entity);
        verify(mapper, times(1)).updateEntity(any(), any());

        verify(service, times(1)).patchFarmer(id, createFarmerDTO);
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

        when(repository.findById(anyLong())).thenThrow(ElementNotFound.class);
        when(mapper.toDTO(entity)).thenThrow(NullPointerException.class);
        when(service.getFarmer(anyLong())).thenThrow(ElementNotFound.class);

        assertThrows(ElementNotFound.class, () -> repository.findById(anyLong()));
        assertThrows(NullPointerException.class, () -> mapper.toDTO(entity));
        assertThrows(ElementNotFound.class, () -> service.getFarmer(anyLong()));

        verify(repository, times(1)).findById(anyLong());
        verify(mapper, times(1)).toDTO(entity);
        verify(service, times(1)).getFarmer(anyLong());
    }


}