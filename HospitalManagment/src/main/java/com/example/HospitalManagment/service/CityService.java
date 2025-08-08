package com.example.HospitalManagment.service;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.city.CreateCity;
import com.example.HospitalManagment.data.city.ViewCity;
import com.example.HospitalManagment.data.nurse.ViewNurse;
import com.example.HospitalManagment.entity.City;
import com.example.HospitalManagment.repository.CityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    public CreateCity createCity(CreateCity createCity) {
        City city = new City();
        if (createCity != null) {

            city.setName(createCity.getName());
            cityRepository.save(city);
        }
    return createCity;
    }

    public ResponseObject getCity() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewCity> nurses=cityRepository.viewAllCity();
        responseObject.setData(nurses);
        return responseObject;
    }

    public Boolean deleteCity(Long id) {
        City city = cityRepository.findById(id).orElseThrow(()-> new RuntimeException("City Not Found"));
        city.setDeletedAt(LocalDateTime.now());
        cityRepository.save(city);
        return true;
    }

    public CreateCity updateCity(CreateCity createCity) {
        City city = cityRepository.findById(createCity.getId()).orElseThrow(()-> new RuntimeException("City Not Found"));

        city.setName(createCity.getName());

        cityRepository.save(city);
        return createCity;
    }


}
