package de.telran.telran_project_apartments_owners.dto;

import de.telran.telran_project_apartments_owners.entity.Apartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuildingRequestDTO {
    private String street;
    private String house;
    private List<Apartment> apartments;
}
