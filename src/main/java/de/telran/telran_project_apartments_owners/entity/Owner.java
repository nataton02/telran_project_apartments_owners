package de.telran.telran_project_apartments_owners.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "owner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique=true)
    private String name;

    @JoinColumn(name = "apartment_id")
    @ManyToOne
    private Apartment apartment;

}
