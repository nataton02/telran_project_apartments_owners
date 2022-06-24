package de.telran.telran_project_apartments_owners.repository;

import de.telran.telran_project_apartments_owners.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

}
