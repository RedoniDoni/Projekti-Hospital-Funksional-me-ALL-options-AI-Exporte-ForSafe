package com.example.HospitalManagment.controller;


import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.appointment.CreateAppointment;
import com.example.HospitalManagment.data.nurse.CreateNurse;
import com.example.HospitalManagment.service.AppointmentService;
import com.example.HospitalManagment.service.NurseService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequestMapping("${base.url}/appointment")
@RestController
@AllArgsConstructor
public class AppointmentController {


    private final NurseService nurseService;
    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        String methodName="get Alll appointments";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=appointmentService.getAppointments();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<CreateAppointment> addAppointment( @RequestBody @Valid CreateAppointment createNurse)  {
        CreateAppointment createdNurse=appointmentService.createAppointment(createNurse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNurse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreateAppointment> updateAppointment(@PathVariable Long id, @RequestBody @Valid CreateAppointment createNurse) {
        String methodName="updateNurse";
        CreateAppointment create=appointmentService.updateAppointment(createNurse,id);
        log.info("executing {}" + methodName);
        return ResponseEntity.status(HttpStatus.OK).body(create);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteAppointment(@PathVariable Long id) {
        String methodName="deleteAppointment";
        appointmentService.deleteAppointment(id);
        log.info("executing {}" + methodName);
        return Boolean.TRUE;
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        String methodName = "getAppointmentsByDoctorId";
        log.info("executing {} with doctorId: {}", methodName, doctorId);
        ResponseObject responseObject = appointmentService.getAppointmentsByDoctorId(doctorId);
        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

}
