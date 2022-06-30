package de.telran.telran_project_apartments_owners.service;

import de.telran.telran_project_apartments_owners.dto.BuildingBulkRequestDTO;

import java.util.List;

public interface BuildingBulkService {

    void createBuildingsBulk(List<BuildingBulkRequestDTO> buildings);
}
