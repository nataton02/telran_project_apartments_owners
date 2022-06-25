package de.telran.telran_project_apartments_owners.service.impl;

import de.telran.telran_project_apartments_owners.dto.OwnerRequestDTO;
import de.telran.telran_project_apartments_owners.entity.Apartment;
import de.telran.telran_project_apartments_owners.entity.Building;
import de.telran.telran_project_apartments_owners.entity.Owner;
import de.telran.telran_project_apartments_owners.repository.ApartmentRepository;
import de.telran.telran_project_apartments_owners.repository.BuildingRepository;
import de.telran.telran_project_apartments_owners.repository.OwnerRepository;
import de.telran.telran_project_apartments_owners.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;


    @Override
    public void createOwner(OwnerRequestDTO request) {
        ownerRepository.save(mapDtoToOwner(request));
    }

    @Override
    public void editOwner(Long buildingId, Long apartmentId, Long ownerId) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Building with id %s does not exist", buildingId)));

        Apartment apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Apartment with id %s does not exist", apartmentId)));

        if(!apartment.getBuilding().equals(building)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Apartment with id %s does not exist in building with id %s",
                            apartmentId, buildingId));
        }

        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("The owner with id %s does not exist", ownerId)));

        if(owner.getApartment() != null && owner.getApartment().getId().equals(apartmentId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("The owner with id %s already lives in the apartment with id %s",
                            ownerId, apartmentId));
        }
        else
            owner.setApartment(apartment);

        ownerRepository.save(owner);
    }

    private Owner mapDtoToOwner(OwnerRequestDTO request) {
        return Owner.builder()
                .name(request.getName())
                .apartment(null)
                .build();
    }
}
