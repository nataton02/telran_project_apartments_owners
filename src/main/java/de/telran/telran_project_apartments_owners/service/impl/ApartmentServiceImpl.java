package de.telran.telran_project_apartments_owners.service.impl;

import de.telran.telran_project_apartments_owners.dto.ApartmentRequestDTO;
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

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    /*@Override
    public void createApartment(ApartmentRequestDTO request) {
        Apartment apartment = mapDtoToApartment(request);
        if(buildingRepository.findById(request.)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Building with street %s and house %s already exists",
                            request.getStreet(), request.getHouse()));
        }
        apartmentRepository.save(apartment);
    }*/




    private Apartment mapDtoToApartment(ApartmentRequestDTO request) {
        return Apartment.builder()
                .apartmentNumber(request.getApartmentNumber())
                .hasBalcony(request.getHasBalcony())
                .building(request.getBuilding())
                .build();
    }
}
