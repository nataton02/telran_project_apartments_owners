package de.telran.telran_project_apartments_owners.controller;

import de.telran.telran_project_apartments_owners.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;


}
