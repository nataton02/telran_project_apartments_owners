package de.telran.telran_project_apartments_owners.service.impl;

import de.telran.telran_project_apartments_owners.dto.BuildingRequestDTO;
import de.telran.telran_project_apartments_owners.dto.BuildingResponseDTO;
import de.telran.telran_project_apartments_owners.entity.Apartment;
import de.telran.telran_project_apartments_owners.entity.Building;
import de.telran.telran_project_apartments_owners.repository.ApartmentRepository;
import de.telran.telran_project_apartments_owners.repository.BuildingRepository;
import de.telran.telran_project_apartments_owners.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public void createBuilding(BuildingRequestDTO request, Integer count) {
        if(buildingRepository.findByStreetAndHouse(request.getStreet(), request.getHouse()) == null) {
            Building building = mapDtoToBuilding(request);
            buildingRepository.save(building);

            if (count != null) {
                List<Apartment> apartments = new ArrayList<>();
                while (apartments.size() <= count) {
                    apartments.add(new Apartment());
                }
                for (Apartment apartment : apartments) {
                    apartment.setBuilding(building);
                    apartmentRepository.save(apartment);
                }
            }
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Building with street %s and house %s already exists",
                            request.getStreet(), request.getHouse()));
        }
    }

    @Override
    public List<BuildingResponseDTO> getAllBuildings(String street) {
        if(street != null) {
            return buildingRepository.findAllByStreetIgnoreCase(street)
                    .stream()
                    .map(this::mapBuildingToDto)
                    .collect(Collectors.toList());
        }
        else {
            return buildingRepository.findAll()
                    .stream()
                    .map(this::mapBuildingToDto)
                    .collect(Collectors.toList());
        }
    }


    private Building mapDtoToBuilding(BuildingRequestDTO request) {
        return Building.builder()
                .street(request.getStreet())
                .house(request.getHouse())
                .build();
    }

    private BuildingResponseDTO mapBuildingToDto(Building building) {
        List<Apartment> apartments = apartmentRepository.findAllByBuilding(building);
        return BuildingResponseDTO.builder()
                .id(building.getId())
                .address(building.getStreet() + " " + building.getHouse())
                .apartmentsCount(apartments.size())
                .build();
    }

}
