package com.example.restdemo.controller;

import com.example.restdemo.dto.CityDTO;
import com.example.restdemo.dto.ResponseDTO;
import com.example.restdemo.dto.StateDTO;
import com.example.restdemo.service.CityService;
import com.example.restdemo.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class CommonController {

    private final StateService stateService;
    private final CityService cityService;

    @GetMapping("/getAllStates")
    public ResponseEntity<ResponseDTO<List<StateDTO>>> fetchAllStates() {
        List<StateDTO> stateDTOs = stateService.fetchStates();

        ResponseDTO<List<StateDTO>> response =
                ResponseDTO.<List<StateDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("states fetched successfully")
                        .data(stateDTOs)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/getAllCities")
    public ResponseEntity<ResponseDTO<List<CityDTO>>> fetchAllCities() {
        List<CityDTO> cityDTOs = cityService.fetchCities();

        ResponseDTO<List<CityDTO>> response =
                ResponseDTO.<List<CityDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("cities fetched successfully")
                        .data(cityDTOs)
                        .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


}
