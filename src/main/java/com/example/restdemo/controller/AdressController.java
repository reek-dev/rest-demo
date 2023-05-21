package com.example.restdemo.controller;

import com.example.restdemo.dto.StateDTO;
import com.example.restdemo.entity.City;
import com.example.restdemo.entity.Organisation;
import com.example.restdemo.entity.State;
import com.example.restdemo.service.CityService;
import com.example.restdemo.service.OrganisationService;
import com.example.restdemo.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AdressController {

    private final CityService cityService;
    private final StateService stateService;
    private final OrganisationService organizationService;


    @PostMapping("/create-state")
    public ResponseEntity<String> createState(
            @RequestBody State state
            ) {
        State newState = stateService.createState(state);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("State `" + newState.getName() + "` was successfully created.");
    }

    @PostMapping("/create-city")
    public ResponseEntity<String> createCity(
            @RequestBody City city,
            @RequestParam(name = "stateId", required = true) Long stateId
            ) {

        city.setState(stateService.getStateById(stateId));

        City newCity = cityService.createCity(city);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("City `" + newCity.getName() + "` was successfully created.");

    }

    @PostMapping("/create-organisation")
    public ResponseEntity<String> createOrganisation(
            @RequestBody Organisation organisation

            ) {
        Organisation newOrganisation = organizationService.createOrganisation(organisation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Organisation `" + newOrganisation.getName() + "` was successfully created.");
    }
}
