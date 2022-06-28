package de.telran.telran_project_apartments_owners.repository;

import de.telran.telran_project_apartments_owners.entity.Apartment;
import de.telran.telran_project_apartments_owners.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findAllByApartment(Apartment apartment);

    Long countAllByApartment(Apartment apartment);

    Owner findByIdAndApartmentId(Long ownerId, Long apartmentId);

    List<Owner> saveAllByApartment(Apartment apartment);
}
