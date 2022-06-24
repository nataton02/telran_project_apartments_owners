package de.telran.telran_project_apartments_owners.service;


import de.telran.telran_project_apartments_owners.dto.BuildingRequestDTO;

public interface BuildingService {
    void createBuilding(BuildingRequestDTO request, Integer count);


}
