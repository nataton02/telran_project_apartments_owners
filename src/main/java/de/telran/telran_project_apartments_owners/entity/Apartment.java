package de.telran.telran_project_apartments_owners.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "apartment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "apartment_number")
    private Integer apartmentNumber;

    @Column(name = "has_balcony")
    private Boolean hasBalcony;

    @JoinColumn(name = "building_id")
    @ManyToOne
    private Building building;
}
