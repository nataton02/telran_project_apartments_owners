package de.telran.telran_project_apartments_owners.repository;

import de.telran.telran_project_apartments_owners.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building findByStreetAndHouse(String street, String house);

}
