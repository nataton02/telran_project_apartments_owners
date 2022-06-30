package de.telran.telran_project_apartments_owners.service.impl;

import de.telran.telran_project_apartments_owners.dto.OwnerRequestDTO;
import de.telran.telran_project_apartments_owners.dto.OwnerResponseDTO;
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

import javax.transaction.Transactional;

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
        ownerRepository.save(convertDtoToOwner(request));
    }

    @Override
    public void toggleOwner(Long buildingId, Long apartmentId, Long ownerId) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Building with id %s does not exist", buildingId)));

        Apartment apartment = apartmentRepository.findByIdAndBuildingId(apartmentId, buildingId);
        if(apartment == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Apartment with id %s does not exist in building width id %s", apartmentId, buildingId));
        }

        Owner owner = findOwnerByIdOrThrow(ownerId);

        if(ownerRepository.findByIdAndApartmentId(ownerId, apartmentId) == null) {
            owner.setApartment(apartment);
        } else {
            owner.setApartment(null);
        }

        ownerRepository.save(owner);
    }

    @Override
    public OwnerResponseDTO getOwnerById(Long ownerId) {

        Owner owner =  findOwnerByIdOrThrow(ownerId);
        return convertOwnerToDto(owner);
    }

    private Owner findOwnerByIdOrThrow(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("The owner with id %s does not exist", ownerId)));
    }

    private OwnerResponseDTO convertOwnerToDto(Owner owner) {
        return OwnerResponseDTO.builder()
                .name(owner.getName())
                .apartment(owner.getApartment())
                .build();
    }

    private Owner convertDtoToOwner(OwnerRequestDTO request) {
        return Owner.builder()
                .name(request.getName())
                .apartment(null)
                .build();
    }
}
