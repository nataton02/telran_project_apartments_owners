package de.telran.telran_project_apartments_owners.service;


import de.telran.telran_project_apartments_owners.dto.*;
import de.telran.telran_project_apartments_owners.entity.Apartment;

import java.util.List;

public interface BuildingService {
    void createBuilding(BuildingRequestDTO request, Integer count);

    List<BuildingResponseDTO> getAllBuildings(String street);
}
