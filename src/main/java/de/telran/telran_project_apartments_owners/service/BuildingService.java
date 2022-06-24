package de.telran.telran_project_apartments_owners.service;


import de.telran.telran_project_apartments_owners.dto.BuildingRequestDTO;
import de.telran.telran_project_apartments_owners.dto.BuildingResponseDTO;

import java.util.List;

public interface BuildingService {
    void createBuilding(BuildingRequestDTO request, Integer count);

    List<BuildingResponseDTO> getAllBuildings(String street);
}
