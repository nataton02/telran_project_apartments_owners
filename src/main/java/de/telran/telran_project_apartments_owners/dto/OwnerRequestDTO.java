package de.telran.telran_project_apartments_owners.dto;

import de.telran.telran_project_apartments_owners.entity.Apartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OwnerRequestDTO {
    private String name;
    private Long apartmentId;
}
