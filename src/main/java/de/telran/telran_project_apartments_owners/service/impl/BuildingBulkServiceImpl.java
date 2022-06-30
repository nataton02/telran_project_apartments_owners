package de.telran.telran_project_apartments_owners.service.impl;

import de.telran.telran_project_apartments_owners.dto.BuildingBulkRequestDTO;
import de.telran.telran_project_apartments_owners.entity.Apartment;
import de.telran.telran_project_apartments_owners.entity.Building;
import de.telran.telran_project_apartments_owners.entity.Owner;
import de.telran.telran_project_apartments_owners.repository.ApartmentRepository;
import de.telran.telran_project_apartments_owners.repository.BuildingRepository;
import de.telran.telran_project_apartments_owners.repository.OwnerRepository;
import de.telran.telran_project_apartments_owners.service.BuildingBulkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BuildingBulkServiceImpl implements BuildingBulkService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    @Transactional
    public void createBuildingsBulk(List<BuildingBulkRequestDTO> buildingsDTO) {
        buildingsDTO.stream()
                .filter(buildingDTO -> !buildingRepository.existsByStreetAndHouse(
                        buildingDTO.getStreet(), buildingDTO.getHouse()))
                .forEach(buildingDTO -> {
                    Building building = Building.builder()
                            .street(buildingDTO.getStreet())
                            .house(buildingDTO.getHouse())
                            .build();
                    buildingRepository.save(building);

                    var apartmentsDTO = buildingDTO.getApartments();

                    if(apartmentsDTO == null || apartmentsDTO.isEmpty()) {
                        return;
                    }

                    apartmentsDTO
                            .forEach(apartmentDTO -> {
                                var apartment = Apartment.builder()
                                        .apartmentNumber(apartmentDTO.getApartmentNumber())
                                        .hasBalcony(apartmentDTO.getHasBalcony())
                                        .building(building)
                                        .build();
                                apartmentRepository.save(apartment);

                                var ownersDTO = apartmentDTO.getOwners();

                                if(ownersDTO == null || ownersDTO.isEmpty()) {
                                    return;
                                }
                                ownersDTO
                                        .forEach(ownerDTO -> {
                                            var owner = Owner.builder()
                                                    .name(ownerDTO.getName())
                                                    .apartment(apartment)
                                                    .build();
                                            ownerRepository.save(owner);
                                        });
                            });
                });
    }
}
