package com.example.HospitalManagment.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.RegisterRequestForAllEntityDTO;
import com.example.HospitalManagment.data.departament.CreateDepartament;
import com.example.HospitalManagment.data.nurse.CreateNurse;
import com.example.HospitalManagment.data.nurse.ViewNurse;
import com.example.HospitalManagment.entity.City;
import com.example.HospitalManagment.entity.Department;
import com.example.HospitalManagment.entity.Nurse;
import com.example.HospitalManagment.entity.Room;
import com.example.HospitalManagment.repository.CityRepository;
import com.example.HospitalManagment.repository.DepartamentRepository;
import com.example.HospitalManagment.repository.NurseRepository;
import com.example.HospitalManagment.repository.RoomRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor

public class NurseService {
    private final NurseRepository nurseRepository;
    private final RoomRepository roomRepository;
    private final EmailService emailService;
    private final DepartamentRepository departamentRepository;
    private final CityRepository cityRepository;

    public ResponseObject getNurses() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewNurse> nurses=nurseRepository.viewAllNurses();
        responseObject.setData(nurses);
        return responseObject;
    }

    public void createNurse(RegisterRequestForAllEntityDTO createNurse) throws MessagingException, IOException {

        Nurse nurse = new Nurse();

        if(createNurse!= null) {
            Department department = departamentRepository.findById(createNurse.getDepartmentId()).orElseThrow(()->new NotFoundException("Departament with id :"+createNurse.getDepartmentId()+" not found"));
            City city = cityRepository.findById(createNurse.getCityId()).orElseThrow(()->new NotFoundException("City with id :"+createNurse.getCityId()+" not found"));
            Room room = roomRepository.findById(createNurse.getRoomId()).orElseThrow(()->new NotFoundException("Room with id:"+ createNurse.getRoomId()+"  not found"));


            nurse.setDescription(createNurse.getDescription());
            nurse.setFirstName(createNurse.getFirstName());
            nurse.setLastName(createNurse.getLastName());
            nurse.setEmail(createNurse.getEmail());
            nurse.setStreet(createNurse.getStreet());
            nurse.setPhoneNumber(createNurse.getPhoneNumber());
            nurse.setRoom(room);
            nurse.setDepartment(department);
            nurse.setCity(city);
            nurse.setCategory(createNurse.getCategory());
            nurse.setEmail(createNurse.getEmail());

            nurseRepository.save(nurse);

            emailService.sendWelcomeEmailToNurse(nurse.getId());
        }

    }

    public CreateNurse updateNurse(CreateNurse updateNurse,Long id){

        Nurse nurse = nurseRepository.findById(id).orElseThrow(()->new RuntimeException("Nurse not found"));

        nurse.setCategory(updateNurse.getCategory());
        nurse.setFirstName(updateNurse.getFirstName());
        nurse.setLastName(updateNurse.getLastName());
        nurse.setEmail(updateNurse.getEmail());
        nurse.setDescription(nurse.getDescription());
        nurse.setStreet(updateNurse.getStreet());
        nurse.setPhoneNumber(updateNurse.getPhoneNumber());

        Department department = departamentRepository.findById(updateNurse.getDepartmentId()).orElseThrow(()->new RuntimeException("Departament with id:"+ updateNurse.getDepartmentId()+"  not found"));
        nurse.setDepartment(department);

        City city= cityRepository.findById(updateNurse.getCityId()).orElseThrow(()->new RuntimeException("City with id:"+ updateNurse.getCityId()+"  not found"));
        nurse.setCity(city);

        Room room = roomRepository.findById(updateNurse.getRoomId()).orElseThrow(()->new RuntimeException("Room with id:"+ updateNurse.getRoomId()+"  not found"));
        nurse.setRoom(room);

        nurseRepository.save(nurse);
        return updateNurse;

    }

    public Boolean deleteNurse(Long id){
        Nurse nurse = nurseRepository.findById(id).orElseThrow(()->new RuntimeException("Nurse not found"));

        nurse.setDeletedAt(LocalDateTime.now());
        nurseRepository.save(nurse);

        return true;
    }
    public ResponseObject getNurseById(Long id) {
        ResponseObject responseObject = new ResponseObject();
        ViewNurse nurse = nurseRepository.findViewNurseById(id)
                .orElseThrow(() -> new RuntimeException("Nurse with ID " + id + " not found"));
        responseObject.setData(nurse);
        responseObject.setStatus(HttpStatus.OK.value());
        return responseObject;
    }

    public long countSoftDeletedNurses() {
        return nurseRepository.countSoftDeletedNurses();
    }
}