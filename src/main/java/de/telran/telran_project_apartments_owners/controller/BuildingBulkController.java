package de.telran.telran_project_apartments_owners.controller;

import de.telran.telran_project_apartments_owners.dto.BuildingBulkRequestDTO;
import de.telran.telran_project_apartments_owners.service.BuildingBulkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingBulkController {

    @Autowired
    private BuildingBulkService buildingBulkService;


    @PostMapping("api/buildings/bulk")
        public void createBuildingsBulk(@RequestBody List<BuildingBulkRequestDTO> request) {
            buildingBulkService.createBuildingsBulk(request);
        }
}
