package ru.farmersregister.farmersregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
