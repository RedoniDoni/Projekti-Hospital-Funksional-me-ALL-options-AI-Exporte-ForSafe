package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.Room.RoomPatientsDTO;
import com.example.HospitalManagment.data.appointment.PatientHistoryDTO;
import com.example.HospitalManagment.data.patient.CreatePatient;
import com.example.HospitalManagment.data.patient.ViewPatient;
import com.example.HospitalManagment.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("${base.url}/patient")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @Operation(summary = "Get all tickets", description = "Returns all tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ViewPatient.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Patients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/all")
    public ResponseEntity getAllPatients() {
        String methodName = "getAllPatients";
        log.info("{} -> Get all patients", methodName);
        ResponseObject responseObject = patientService.getAllPatients();
        responseObject.setStatus(HttpStatus.OK.value());
        log.info("{} -> Get all patient, response status: {}", methodName, responseObject.getCode());
        return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
    }

//    @Operation(summary = "Update Ticket", description = "Update an existing ticket by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Ticket updated", content = @Content(schema = @Schema(implementation = CreatePatient.class))),
//            @ApiResponse(responseCode = "400", description = "Invalid input"),
//            @ApiResponse(responseCode = "404", description = "Ticket not found"),
//            @ApiResponse(responseCode = "409", description = "Conflict")
//    })
//    @PostMapping("/create")
//    public ResponseEntity<CreatePatient> createPatient(@RequestBody @Valid CreatePatient createPatient) throws MessagingException, IOException {
//        String methodName = "createTicket";
//        log.info("{} -> Create Ticket", methodName);
//        CreatePatient createPatient1 = patientService.createPatient(createPatient);
//        log.info("{} -> Create ticket, response status: 200", methodName);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createPatient1);
//    }


    @Operation(summary = "Update Ticket", description = "Update an existing ticket by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket updated", content = @Content(schema = @Schema(implementation = CreatePatient.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Ticket not found"),
            @ApiResponse(responseCode = "409", description = "Conflict")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<CreatePatient> updateTicketById(@PathVariable Long id, @RequestBody @Valid CreatePatient updatePatient) {
        String methodName = "updateTicketById";
        log.info("{} -> Update Ticket", methodName);
        CreatePatient ticketView = patientService.updatePatient(id, updatePatient);
        log.info("{} -> Update ticket, response status: 200", methodName);
        return ResponseEntity.status(HttpStatus.OK).body(ticketView);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        String methodName = "delete";
        log.info("{} -> Delete Ticket", methodName);
        patientService.deletePatient(id);
        return Boolean.TRUE;
    }
    @GetMapping("/{id}")
    public ResponseEntity getPatientById(@PathVariable Long id) {
        String methodName = "getNurseById";
        log.info("executing {}", methodName);
        ResponseObject responseObject = patientService.getPatientById(id);
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

    @GetMapping("/{patientId}/history")
    public ResponseEntity<PatientHistoryDTO> getPatientHistory(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.getPatientHistory(patientId));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ViewPatient>> getPatientsByRoomId(@PathVariable Long roomId) {
        String methodName = "getPatientsByRoomId";
        log.info("{} -> Get patients by roomId: {}", methodName, roomId);
        List<ViewPatient> patients = patientService.getPatientsByRoomId(roomId);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/rooms")
    public List<RoomPatientsDTO> getPatientsGroupedByRoom() {
        return patientService.getPatientsGroupedByRoom();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getSoftDeletedPatientCount() {
        String methodName = "getSoftDeletedPatientCount";
        log.info("{} -> Get total soft-deleted patient count", methodName);
        long deletedPatientCount = patientService.countSoftDeletedPatients();
        log.info("{} -> Total soft-deleted patients: {}", methodName, deletedPatientCount);
        return ResponseEntity.ok(deletedPatientCount);
    }

}
