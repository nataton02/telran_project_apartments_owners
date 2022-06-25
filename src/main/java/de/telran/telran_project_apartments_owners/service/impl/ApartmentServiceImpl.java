package de.telran.telran_project_apartments_owners.service.impl;

import de.telran.telran_project_apartments_owners.dto.ApartmentResponseDTO;
import de.telran.telran_project_apartments_owners.entity.Apartment;
import de.telran.telran_project_apartments_owners.entity.Building;
import de.telran.telran_project_apartments_owners.entity.Owner;
import de.telran.telran_project_apartments_owners.repository.ApartmentRepository;
import de.telran.telran_project_apartments_owners.repository.BuildingRepository;
import de.telran.telran_project_apartments_owners.repository.OwnerRepository;
import de.telran.telran_project_apartments_owners.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<ApartmentResponseDTO> getApartments(Long buildingId, boolean hasOwners) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Building with id %s does not exist", buildingId)));

        if(hasOwners) {
            return apartmentRepository.findAllByBuilding(building)
                    .stream()
                    .filter(apartment -> ownerRepository.findAllByApartment(apartment).size() != 0)
                    .map(this::mapApartmentToDto)
                    .collect(Collectors.toList());
        }
        else {
            return apartmentRepository.findAllByBuilding(building)
                    .stream()
                    .map(this::mapApartmentToDto)
                    .collect(Collectors.toList());
        }
    }

    private ApartmentResponseDTO mapApartmentToDto(Apartment apartment) {
        List<Owner> owners = ownerRepository.findAllByApartment(apartment);
        return ApartmentResponseDTO.builder()
                .id(apartment.getId())
                .apartmentNumber(apartment.getApartmentNumber())
                .owners(owners)
                .build();
    }
}
