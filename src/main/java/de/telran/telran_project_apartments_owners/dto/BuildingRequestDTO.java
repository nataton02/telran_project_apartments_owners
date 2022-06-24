package de.telran.telran_project_apartments_owners.dto;

import de.telran.telran_project_apartments_owners.entity.Apartment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class BuildingRequestDTO {
    private String street;
    private String house;
    List<Apartment> apartments;
}
