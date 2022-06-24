package de.telran.telran_project_apartments_owners.controller;

import de.telran.telran_project_apartments_owners.dto.ErrorResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponseDTO handleError(ResponseStatusException ex) {

        return ErrorResponseDTO.builder()
                .status(ex.getStatus())
                .message(ex.getReason())
                .build();
    }


}
