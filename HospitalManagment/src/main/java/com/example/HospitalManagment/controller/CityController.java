package com.example.HospitalManagment.controller;


import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.city.CreateCity;
import com.example.HospitalManagment.service.CityService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//localhost:8080/api/city/all
@Log4j2
@RequestMapping("${base.url}/city")
@RestController
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<CreateCity> createPatient(@RequestBody @Valid CreateCity createCity) {
        CreateCity createCity1 = cityService.createCity(createCity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCity1);
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        ResponseObject responseObject = cityService.getCity();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteCity(@PathVariable Long id) {
        return cityService.deleteCity(id);
    }

    @PutMapping("/update")
    public CreateCity updateCity(@RequestBody @Valid CreateCity createCity) {
        return cityService.updateCity(createCity);
    }

}
