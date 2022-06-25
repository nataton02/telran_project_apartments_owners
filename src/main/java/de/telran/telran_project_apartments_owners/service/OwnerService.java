package de.telran.telran_project_apartments_owners.service;

import de.telran.telran_project_apartments_owners.dto.OwnerRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
    void createOwner(OwnerRequestDTO request);

    void editOwner(Long buildingId, Long apartmentId, Long ownerId);
}
