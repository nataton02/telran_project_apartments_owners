package de.telran.telran_project_apartments_owners.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuildingBulkRequestDTO {

    private String street;
    private  String house;
    private List<ApartmentBulkRequestDTO> apartments;
}
