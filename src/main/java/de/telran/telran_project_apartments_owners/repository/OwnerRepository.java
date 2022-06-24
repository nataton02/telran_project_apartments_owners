package de.telran.telran_project_apartments_owners.repository;

import de.telran.telran_project_apartments_owners.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
