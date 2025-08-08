package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.departament.CreateDepartament;
import com.example.HospitalManagment.service.DepartamentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("${base.url}/departament")
@AllArgsConstructor
public class DepartamentController {

    private final DepartamentService departamentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        String methodName="get Alll departament";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=departamentService.getDepartaments();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateDepartament> addDepartament(@RequestBody @Valid CreateDepartament createDepartament) {
        CreateDepartament createdDepartament=departamentService.createDepartament(createDepartament);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartament);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreateDepartament> updateDepartament(@PathVariable Long id,@RequestBody @Valid CreateDepartament createDepartament) {
        String methodName="updateDepartment";
        CreateDepartament create=departamentService.updateDepartament(createDepartament,id);
        log.info("executing {}" + methodName);
        return ResponseEntity.status(HttpStatus.OK).body(create);
    }

    @DeleteMapping("/deleted")
    public Boolean deleteDepartament(@PathVariable Long id) {
        String methodName="deleteDepartament";
        departamentService.deleteDepartament(id);
        log.info("executing {}" + methodName);
        return Boolean.TRUE;
    }
}
