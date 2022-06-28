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
public class ApartmentRequestDTO {

    private Integer apartmentNumber;
    private Boolean hasBalcony;
    private Long buildingId;
    private List<Owner> owners;
}
