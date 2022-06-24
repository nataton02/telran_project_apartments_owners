package de.telran.telran_project_apartments_owners.controller;

import de.telran.telran_project_apartments_owners.dto.BuildingRequestDTO;
import de.telran.telran_project_apartments_owners.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/api/buildings")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBuilding(@RequestBody BuildingRequestDTO request,
            @RequestParam(name="apartmentsCount", required = false) Integer count) {
        buildingService.createBuilding(request, count);
    }


}
