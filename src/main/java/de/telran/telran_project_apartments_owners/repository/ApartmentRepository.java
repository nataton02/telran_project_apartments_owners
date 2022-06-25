package de.telran.telran_project_apartments_owners.repository;

import de.telran.telran_project_apartments_owners.entity.Apartment;
import de.telran.telran_project_apartments_owners.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByBuilding(Building building);




}
