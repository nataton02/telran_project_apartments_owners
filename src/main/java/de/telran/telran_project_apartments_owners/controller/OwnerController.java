package de.telran.telran_project_apartments_owners.controller;

import de.telran.telran_project_apartments_owners.dto.OwnerRequestDTO;
import de.telran.telran_project_apartments_owners.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/api/owners")
    public void createOwner(@RequestBody OwnerRequestDTO request) {
        ownerService.createOwner(request);
    }

    @PutMapping("/api/buildings/{buildingId}/apartments/{apartmentId}/owners/{ownerId}")
    public void editOwner(@PathVariable("buildingId") Long buildingId,
                              @PathVariable("apartmentId") Long apartmentId,
                              @PathVariable("ownerId") Long ownerId) {
        ownerService.editOwner(buildingId, apartmentId, ownerId);
    }
}
