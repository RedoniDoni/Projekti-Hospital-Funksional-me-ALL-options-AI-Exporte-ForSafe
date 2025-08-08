package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.diagnosis.CreateDiagnosis;
import com.example.HospitalManagment.service.DiagnosisService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequestMapping("${base.url}/diagnosis")
@RestController
@AllArgsConstructor
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        String methodName = "getAllDiagnoses";
        log.info("Executing {}", methodName);

        ResponseObject responseObject = diagnosisService.getDiagnoses();
        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateDiagnosis> addDiagnosis(@RequestBody @Valid CreateDiagnosis createDiagnosis) {
        String methodName = "createDiagnosis";
        log.info("Executing {}", methodName);

        CreateDiagnosis createdDiagnosis = diagnosisService.createDiagnosis(createDiagnosis);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiagnosis);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreateDiagnosis> updateDiagnosis(@PathVariable Long id, @RequestBody @Valid CreateDiagnosis createDiagnosis) {
        String methodName = "updateDiagnosis";
        log.info("Executing {}", methodName);

        CreateDiagnosis updatedDiagnosis = diagnosisService.updateDiagnosis(createDiagnosis, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDiagnosis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDiagnosis(@PathVariable Long id) {
        String methodName = "deleteDiagnosis";
        log.info("Executing {}", methodName);

        Boolean deleted = diagnosisService.deleteDiagnosis(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
    @GetMapping("/{patientId}")
    public ResponseEntity<ResponseObject> getDiagnosesByPatientId(@PathVariable Long patientId) {
        String methodName = "getDiagnosesByPatientId";
        log.info("Executing {} for patientId: {}", methodName, patientId);

        ResponseObject responseObject = diagnosisService.getDiagnosesByPatientId(patientId);
        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ResponseObject> getDiagnosesByDoctorId(@PathVariable Long doctorId) {
        String methodName = "getDiagnosesByDoctorId";
        log.info("Executing {} for doctorId: {}", methodName, doctorId);

        ResponseObject responseObject = diagnosisService.getDiagnosesByDoctorId(doctorId);
        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


}
