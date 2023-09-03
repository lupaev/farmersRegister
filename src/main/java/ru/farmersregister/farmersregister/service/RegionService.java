package ru.farmersregister.farmersregister.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.farmersregister.farmersregister.dto.CreateRegionDTO;
import ru.farmersregister.farmersregister.dto.RegionDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO;
import ru.farmersregister.farmersregister.exception.ElemNotFound;

import java.sql.SQLException;

/**
 * Сервис для сущности районов
 */
public interface RegionService {

    /**
     * Получение всех Районов
     *
     * @return
     */
    Page<RegionDTO> findAll(RequestDTO requestDTO, Pageable pageable);

    /**
     * Получение всех Районов в Архиве
     *
     * @return
     */
    Page<RegionDTO> findAllInArchive(Pageable pageable);

    /**
     * Добавление нового Района в БД
     *
     * @param regionDTO
     * @return
     */
    RegionDTO addRegion(CreateRegionDTO regionDTO);

    /**
     * Изменение данных Района
     *
     * @param id
     * @param regionDTO
     * @return
     * @throws ElemNotFound
     */
    RegionDTO patchRegion(Long id, CreateRegionDTO regionDTO) throws ElemNotFound;

    /**
     * Перемещение Района в архив
     *
     * @param id
     * @return
     * @throws SQLException
     */
    RegionDTO delRegion(Long id) throws SQLException;

}
