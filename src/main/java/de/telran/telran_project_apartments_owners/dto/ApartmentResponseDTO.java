package de.telran.telran_project_apartments_owners.dto;

import de.telran.telran_project_apartments_owners.entity.Building;
import de.telran.telran_project_apartments_owners.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ApartmentResponseDTO {

    private Long id;
    private Integer apartmentNumber;
    private Boolean hasBalcony;
    private List<Owner> owners;
}
