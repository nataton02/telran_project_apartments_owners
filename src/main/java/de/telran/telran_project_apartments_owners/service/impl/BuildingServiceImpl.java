package de.telran.telran_project_apartments_owners.service.impl;

import de.telran.telran_project_apartments_owners.dto.ApartmentRequestDTO;
import de.telran.telran_project_apartments_owners.dto.BuildingRequestDTO;
import de.telran.telran_project_apartments_owners.dto.BuildingResponseDTO;
import de.telran.telran_project_apartments_owners.dto.OwnerRequestDTO;
import de.telran.telran_project_apartments_owners.entity.Apartment;
import de.telran.telran_project_apartments_owners.entity.Building;
import de.telran.telran_project_apartments_owners.entity.Owner;
import de.telran.telran_project_apartments_owners.repository.ApartmentRepository;
import de.telran.telran_project_apartments_owners.repository.BuildingRepository;
import de.telran.telran_project_apartments_owners.repository.OwnerRepository;
import de.telran.telran_project_apartments_owners.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Transactional
    @Override
    public void saveOwnerByApartment(Apartment apartment){
        apartmentRepository.save(apartment);
        ownerRepository.saveAllByApartment(apartment);
    }

    @Override
    public void createCity(List<BuildingRequestDTO> requestBuildings,
                           List<ApartmentRequestDTO> requestApartments,
                           OwnerRequestDTO requestOwner) {

        for(var requestBuilding : requestBuildings) {
            if (buildingRepository.findByStreetAndHouse(
                    requestBuilding.getStreet().toLowerCase(),
                    requestBuilding.getHouse().toLowerCase()) != null) {
                continue;
            }

            var building = convertDtoToBuilding(requestBuilding);
            buildingRepository.save(building);


            for (var requestApartment : requestApartments) {

               Apartment apartment = Apartment.builder()
                       .apartmentNumber(requestApartment.getApartmentNumber())
                       .hasBalcony(requestApartment.getHasBalcony())
                       .build();
               apartment.setBuilding(building);

               List<Owner> owners = Stream
                       .generate(() -> Owner.builder()
                               .name(requestOwner.getName())
                               .build()
                       )
                       .peek(owner -> owner.setApartment(apartment))
                       .collect(Collectors.toList());

               saveOwnerByApartment(apartment);
            }
        }
    }


    public void createBuilding(BuildingRequestDTO request, Integer count) {
        if(buildingRepository.findByStreetAndHouse(request.getStreet().toLowerCase(),
                                                    request.getHouse().toLowerCase()) != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Building with street %s and house %s already exists",
                            request.getStreet(), request.getHouse()));
        }

        Building building = convertDtoToBuilding(request);
        buildingRepository.save(building);

        var apartments = Stream.generate(Apartment::new)
                .limit(count)
                .peek(apartment -> apartment.setBuilding(building))
                .collect(Collectors.toList());

        apartmentRepository.saveAll(apartments);
    }

    @Override
    public List<BuildingResponseDTO> getAllBuildings(String street) {

        if(street == null) {
            return buildingRepository.findAll()
                    .stream()
                    .map(building -> {
                        Long apartmentsCount = apartmentRepository.countByBuilding(building);
                        return this.convertBuildingToDto(building, apartmentsCount.intValue());
                    })
                    .collect(Collectors.toList());
        }

        return buildingRepository.findAllByStreetIgnoreCase(street)
                .stream()
                .map(building -> {
                    Long apartmentsCount = apartmentRepository.countByBuilding(building);
                    return this.convertBuildingToDto(building, apartmentsCount.intValue());
                })
                .collect(Collectors.toList());

    }


    private Building convertDtoToBuilding(BuildingRequestDTO request) {
        return Building.builder()
                .street(request.getStreet())
                .house(request.getHouse())
                .build();
    }

    private BuildingResponseDTO convertBuildingToDto(Building building, Integer apartmentsCount) {

        return BuildingResponseDTO.builder()
                .id(building.getId())
                .address(building.getStreet() + " " + building.getHouse())
                .apartmentsCount(apartmentsCount)
                .build();
    }

}
