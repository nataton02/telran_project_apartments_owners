package de.telran.telran_project_apartments_owners.repository;

import de.telran.telran_project_apartments_owners.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building findByStreetAndHouse(String street, String house);

    List<Building> findAllByStreetIgnoreCase(String street);


}
