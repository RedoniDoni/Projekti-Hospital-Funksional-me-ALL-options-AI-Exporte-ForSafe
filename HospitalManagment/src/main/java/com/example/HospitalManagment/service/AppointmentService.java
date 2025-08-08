package com.example.HospitalManagment.service;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.appointment.CreateAppointment;
import com.example.HospitalManagment.data.appointment.ViewAppointment;
import com.example.HospitalManagment.data.city.CreateCity;
import com.example.HospitalManagment.data.nurse.ViewNurse;
import com.example.HospitalManagment.entity.Appointment;
import com.example.HospitalManagment.entity.City;
import com.example.HospitalManagment.entity.Doctor;
import com.example.HospitalManagment.entity.Patient;
import com.example.HospitalManagment.repository.AppointmentRepository;
import com.example.HospitalManagment.repository.CityRepository;
import com.example.HospitalManagment.repository.DoctorRepository;
import com.example.HospitalManagment.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public CreateAppointment createAppointment(CreateAppointment createAppointment) {

        Appointment appointment = new Appointment();
        if (createAppointment != null) {

            Patient patient = patientRepository.findById(createAppointment.getPatientId()).orElseThrow(() -> new RuntimeException("patient not found"));
            appointment.setPatient(patient);

            Doctor doctor = doctorRepository.findById(createAppointment.getDoctorId()).orElseThrow(() -> new RuntimeException("Department not found"));
            appointment.setDoctor(doctor);

            appointment.setReason(createAppointment.getReason());
            appointment.setStatus(createAppointment.getStatus());

            appointmentRepository.save(appointment);
        }
        return createAppointment;
    }


    public ResponseObject getAppointments() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewAppointment> nurses=appointmentRepository.viewAllAppointments();
        responseObject.setData(nurses);
        return responseObject;
    }



    public Boolean deleteAppointment(Long id) {
        Appointment appointment= appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("appointment not found"));
        appointment.setDeletedAt(LocalDateTime.now());
        return true;
    }

    public CreateAppointment updateAppointment(CreateAppointment createAppointment,Long id) {
        Appointment appointment= appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("appointment not found"));

        if (createAppointment != null) {

            Patient patient = patientRepository.findById(createAppointment.getPatientId()).orElseThrow(() -> new RuntimeException("patient not found"));
            appointment.setPatient(patient);

            Doctor doctor = doctorRepository.findById(createAppointment.getDoctorId()).orElseThrow(() -> new RuntimeException("Department not found"));
            appointment.setDoctor(doctor);

            appointment.setReason(createAppointment.getReason());
            appointment.setStatus(createAppointment.getStatus());

            appointmentRepository.save(appointment);
        }
        return createAppointment;
    }

    public ResponseObject getAppointmentsByDoctorId(Long doctorId) {
        ResponseObject responseObject = new ResponseObject();
        List<ViewAppointment> appointments = appointmentRepository.viewAppointmentsByDoctorId(doctorId);
        responseObject.setData(appointments);
        return responseObject;
    }

}
