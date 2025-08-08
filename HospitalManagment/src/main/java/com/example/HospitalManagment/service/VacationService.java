package com.example.HospitalManagment.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.vacation.ViewVacation;
import com.example.HospitalManagment.data.vacation.CreateVacation;
import com.example.HospitalManagment.entity.Doctor;
import com.example.HospitalManagment.entity.Nurse;
import com.example.HospitalManagment.entity.Vacation;
import com.example.HospitalManagment.repository.DoctorRepository;
import com.example.HospitalManagment.repository.NurseRepository;
import com.example.HospitalManagment.repository.VacationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VacationService {
    private final VacationRepository vacationRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;

    public CreateVacation createVacation(CreateVacation createVacation) {
        Vacation vacation = new Vacation();
        if(createVacation!=null){
            vacation.setStartDate(createVacation.getStartDate());
            vacation.setEndDate(createVacation.getEndDate());
            vacation.setReason(createVacation.getReason());
            vacation.setCertification(createVacation.getCertification());
            if(createVacation.getDoctorId()!=null) {
                Doctor doctor = doctorRepository.findById(createVacation.getDoctorId()).orElseThrow(() -> new NotFoundException("doctor not found "));

                vacation.setDoctor(doctor);
            }
            if(createVacation.getNurseId()!=null) {
                Nurse nurse = nurseRepository.findById(createVacation.getNurseId()).orElseThrow(() -> new NotFoundException("nurse not found "));

                vacation.setNurse(nurse);
            }
            vacationRepository.save(vacation);
        }
        return createVacation;
    }

    public CreateVacation updatevacation(Long id, CreateVacation createVacation) {

        Vacation vacation = vacationRepository.findById(id).orElseThrow(() -> new NotFoundException("vacation not found "));

        vacation.setStartDate(createVacation.getStartDate());
        vacation.setEndDate(createVacation.getEndDate());
        vacation.setReason(createVacation.getReason());
        vacation.setCertification(createVacation.getCertification());

        vacationRepository.save(vacation);

        return createVacation;
    }

    public Boolean deleteVacation(Long id) {
        Vacation vacation = vacationRepository.findById(id).orElseThrow(() -> new NotFoundException("vacation not found "));
        vacation.setDeletedAt(LocalDateTime.now());
        vacationRepository.save(vacation);
        return Boolean.TRUE;
    }
    public ResponseObject getAllVacation() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewVacation> vacationList = vacationRepository.getAllVacations();
        responseObject.setData(vacationList);
        return responseObject;
    }

    public ResponseObject getAllNurseVacation() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewVacation> vacationList = vacationRepository.getAllVacationsByNurse();
        responseObject.setData(vacationList);
        return responseObject;
    }

    public ResponseObject getAllDoctorVacation() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewVacation> vacationList = vacationRepository.getAllVacationsByDoctor();
        responseObject.setData(vacationList);
        return responseObject;
    }
}
