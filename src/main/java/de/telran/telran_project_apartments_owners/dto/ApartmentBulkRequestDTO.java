package de.telran.telran_project_apartments_owners.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ApartmentBulkRequestDTO {

    private Boolean hasBalcony;
    private Integer apartmentNumber;
    private List<OwnerBulkRequestDTO> owners;
}
