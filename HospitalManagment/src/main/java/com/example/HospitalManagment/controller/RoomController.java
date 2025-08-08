package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.Room.CreateRoom;
import com.example.HospitalManagment.data.departament.CreateDepartament;
import com.example.HospitalManagment.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("${base.url}/room")
@AllArgsConstructor
public class RoomController {

    private RoomService roomService;


    @GetMapping("/all")
    public ResponseEntity getAll() {
        String methodName="get Alll room";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=roomService.allRooms();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateRoom> addRoom(@RequestBody @Valid CreateRoom createRoom) {
        CreateRoom createRoom1=roomService.createRoom(createRoom);
        return ResponseEntity.status(HttpStatus.CREATED).body(createRoom1);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreateRoom> updateRoom(@PathVariable Long id, @RequestBody @Valid CreateRoom createRoom) {
        String methodName="updateDepartment";
        CreateRoom create=roomService.updateRoom(id,createRoom);
        log.info("executing {}" + methodName);
        return ResponseEntity.status(HttpStatus.OK).body(create);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteRoom(@PathVariable Long id) {
        String methodName="deleteDepartament";
        roomService.deleteRoom(id);
        log.info("executing {}" + methodName);
        return Boolean.TRUE;
    }
    @GetMapping("/{id}")
    public ResponseEntity getRoomById(@PathVariable Long id) {
        String methodName = "getRoomById";
        log.info("executing {}", methodName);
        ResponseObject responseObject = roomService.getRoomById(id);
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }
}
