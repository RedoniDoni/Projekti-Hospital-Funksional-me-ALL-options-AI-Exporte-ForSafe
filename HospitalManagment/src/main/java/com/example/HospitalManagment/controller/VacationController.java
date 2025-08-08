package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.inventory.CreateInventory;
import com.example.HospitalManagment.data.vacation.CreateVacation;
import com.example.HospitalManagment.service.InventoryService;
import com.example.HospitalManagment.service.VacationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("${base.url}/vacation")
@AllArgsConstructor
public class VacationController {
    private final VacationService vacationService;

    @PostMapping("/create")
    public ResponseEntity createVacation(@RequestBody CreateVacation vacation) {
        CreateVacation createVacation = vacationService.createVacation(vacation);

        return ResponseEntity.status(HttpStatus.CREATED).body(createVacation);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateVacation(@RequestBody CreateVacation updateVacation, @PathVariable("id") Long id){
        String methodName = "updateVacation";
        CreateVacation updateVacation1=vacationService.updatevacation(id,updateVacation);
        log.info("executing {}" + methodName);
        return ResponseEntity.status(HttpStatus.OK).body(updateVacation1);
    }

    @DeleteMapping("/deleted/{id}")
    public Boolean deleteVacation(@PathVariable Long id) {
        String methodName="deleteVacation";
        vacationService.deleteVacation(id);
        log.info("executing {}" + methodName);
        return Boolean.TRUE;
    }
    @GetMapping("/all")
    public ResponseEntity getAll() {
        String methodName="get Alll Vacation";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=vacationService.getAllVacation();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }
    @GetMapping("/all-by-nurse")
    public ResponseEntity getAllVacationByNurse() {
        String methodName="get Alll Vacation";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=vacationService.getAllNurseVacation();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }
    @GetMapping("/all-by-doctor")
    public ResponseEntity getAllVacationByDoctor() {
        String methodName="get Alll Vacation";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=vacationService.getAllDoctorVacation();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }
}
