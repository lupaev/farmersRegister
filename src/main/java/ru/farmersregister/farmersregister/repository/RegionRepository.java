package ru.farmersregister.farmersregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.farmersregister.farmersregister.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {


}
