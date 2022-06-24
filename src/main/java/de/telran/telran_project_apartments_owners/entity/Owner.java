package de.telran.telran_project_apartments_owners.entity;

import javax.persistence.*;

@Entity
@Table
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Apartment apartment;

}
