package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.RegisterRequestForAllEntityDTO;
import com.example.HospitalManagment.data.departament.CreateDepartament;
import com.example.HospitalManagment.data.doctor.CreateDoctor;
import com.example.HospitalManagment.service.DepartamentService;
import com.example.HospitalManagment.service.DoctorService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("${base.url}/doctor")
@AllArgsConstructor

public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        String methodName="get All Doctors";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=doctorService.getDoctors();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

//    @PostMapping("/create")
//    public ResponseEntity<CreateDoctor> addDoctor(@RequestBody @Valid RegisterRequestForAllEntityDTO createDoctor) throws MessagingException, IOException {
//        CreateDoctor createdDoctor=doctorService.createDoctor(createDoctor);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreateDoctor> updateDoctor(@PathVariable Long id,@RequestBody @Valid CreateDoctor createDoctor) {
        String methodName="updateDoctor";
        CreateDoctor create=doctorService.updateDoctor(createDoctor,id);
        log.info("executing {}" + methodName);
        return ResponseEntity.status(HttpStatus.OK).body(create);
    }

    @DeleteMapping("/deleted")
    public Boolean deleteDoctor(@PathVariable Long id) {
        String methodName="deleteDoctor";
        doctorService.deleteDoctor(id);
        log.info("executing {}" + methodName);
        return Boolean.TRUE;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getSoftDeletedDoctorCount() {
        String methodName = "getSoftDeletedDoctorCount";
        log.info("{} -> Get total soft-deleted doctor count", methodName);
        long deletedDoctorCount = doctorService.countSoftDeletedDoctors();
        log.info("{} -> Total soft-deleted doctors: {}", methodName, deletedDoctorCount);
        return ResponseEntity.ok(deletedDoctorCount);
    }
}

