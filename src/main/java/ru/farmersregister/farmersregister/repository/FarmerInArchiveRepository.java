package ru.farmersregister.farmersregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.FarmerInArchive;

@Repository
public interface FarmerInArchiveRepository extends JpaRepository<FarmerInArchive, Long> {

}