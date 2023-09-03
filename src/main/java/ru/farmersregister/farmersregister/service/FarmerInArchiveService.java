package ru.farmersregister.farmersregister.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.farmersregister.farmersregister.dto.FarmerDTO;

/**
 * Сервис для сущности фермера в архиве
 */
public interface FarmerInArchiveService {

    /**
     * Получение всех Фермеров в Архиве
     *
     * @return
     */
    Page<FarmerDTO> findAll(Pageable pageable);


}
