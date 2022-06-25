package de.telran.telran_project_apartments_owners.controller;

import de.telran.telran_project_apartments_owners.dto.ApartmentResponseDTO;
import de.telran.telran_project_apartments_owners.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/api/buildings/{buildingId}/apartments")
    public List<ApartmentResponseDTO> getApartments(@PathVariable("buildingId") Long buildingId,
                                                             @RequestParam(name = "hasOwners", required = false)
                                                                     boolean hasOwners) {
        return apartmentService.getApartments(buildingId, hasOwners);
    }


}
