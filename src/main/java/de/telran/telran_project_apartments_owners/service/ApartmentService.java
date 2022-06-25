package de.telran.telran_project_apartments_owners.service;

import de.telran.telran_project_apartments_owners.dto.ApartmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApartmentService {

    List<ApartmentResponseDTO> getApartments(Long buildingId, boolean hasOwners);
}
