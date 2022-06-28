package de.telran.telran_project_apartments_owners.controller;

import de.telran.telran_project_apartments_owners.dto.*;
import de.telran.telran_project_apartments_owners.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/api/buildings")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBuilding(@RequestBody BuildingRequestDTO request,
                               @RequestParam(name = "apartmentsCount", required = false) Integer count) {
        buildingService.createBuilding(request, count);
    }

    @PostMapping("/api/buildings/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBuilding(@RequestBody List<BuildingRequestDTO> requestBuilding,
                               @RequestBody List<ApartmentRequestDTO> requestApartments,
                               @RequestBody OwnerRequestDTO requestOwner) {
        buildingService.createCity(requestBuilding, requestApartments, requestOwner);
    }

    @GetMapping("api/buildings")
    public List<BuildingResponseDTO> getAllBuildings(
            @RequestParam(name = "street", required = false) String street) {
        return buildingService.getAllBuildings(street);
    }


}
