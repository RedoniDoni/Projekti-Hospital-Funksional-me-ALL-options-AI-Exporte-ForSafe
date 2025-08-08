package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.departament.CreateDepartament;
import com.example.HospitalManagment.data.nurse.CreateNurse;
import com.example.HospitalManagment.service.DepartamentService;
import com.example.HospitalManagment.service.NurseService;
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
@RequestMapping("${base.url}/nurse")
@AllArgsConstructor

public class NurseController {
    private final NurseService nurseService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        String methodName="get Alll nurses";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=nurseService.getNurses();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }
//    @PostMapping("/create")
//    public ResponseEntity<CreateNurse> addNurse(@RequestBody @Valid CreateNurse createNurse) throws MessagingException, IOException {
//        CreateNurse createdNurse=nurseService.createNurse(createNurse);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdNurse);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreateNurse> updateNurse(@PathVariable Long id,@RequestBody @Valid CreateNurse createNurse) {
        String methodName="updateNurse";
        CreateNurse create=nurseService.updateNurse(createNurse,id);
        log.info("executing {}" + methodName);
        return ResponseEntity.status(HttpStatus.OK).body(create);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteNurse(@PathVariable Long id) {
        String methodName="deleteNurse";
        nurseService.deleteNurse(id);
        log.info("executing {}" + methodName);
        return Boolean.TRUE;
    }
    @GetMapping("/{id}")
    public ResponseEntity getNurseById(@PathVariable Long id) {
        String methodName = "getNurseById";
        log.info("executing {}", methodName);
        ResponseObject responseObject = nurseService.getNurseById(id);
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getSoftDeletedNurseCount() {
        String methodName = "getSoftDeletedNurseCount";
        log.info("{} -> Get total soft-deleted nurse count", methodName);
        long deletedNurseCount = nurseService.countSoftDeletedNurses();
        log.info("{} -> Total soft-deleted nurses: {}", methodName, deletedNurseCount);
        return ResponseEntity.ok(deletedNurseCount);
    }


}
