package com.example.HospitalManagment.service;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.diagnosis.CreateDiagnosis;
import com.example.HospitalManagment.data.diagnosis.ViewDiagnosis;
import com.example.HospitalManagment.entity.Diagnosis;
import com.example.HospitalManagment.entity.Appointment;
import com.example.HospitalManagment.entity.Doctor;
import com.example.HospitalManagment.entity.Patient;
import com.example.HospitalManagment.repository.DiagnosisRepository;
import com.example.HospitalManagment.repository.AppointmentRepository;
import com.example.HospitalManagment.repository.DoctorRepository;
import com.example.HospitalManagment.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public CreateDiagnosis createDiagnosis(CreateDiagnosis createDiagnosis) {
        Diagnosis diagnosis = new Diagnosis();
        if (createDiagnosis != null) {
            // Retrieve the appointment, doctor, and patient from the IDs provided in the request
            Appointment appointment = appointmentRepository.findById(createDiagnosis.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));
            diagnosis.setAppointment(appointment);

            Doctor doctor = doctorRepository.findById(createDiagnosis.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            diagnosis.setDoctor(doctor);

            Patient patient = patientRepository.findById(createDiagnosis.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            diagnosis.setPatient(patient);

            // Set the diagnosis details and treatment plan
            diagnosis.setDiagnosisDetails(createDiagnosis.getDiagnosisDetails());
            diagnosis.setTreatmentPlan(createDiagnosis.getTreatmentPlan());

            // Save the diagnosis entity
            diagnosisRepository.save(diagnosis);
        }
        return createDiagnosis;
    }

    public ResponseObject getDiagnoses() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewDiagnosis> diagnoses = diagnosisRepository.viewAllDiagnosis(); // Assuming you have a custom query to map to ViewDiagnosis
        responseObject.setData(diagnoses);
        return responseObject;
    }

    public Boolean deleteDiagnosis(Long id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnosis not found"));
        diagnosis.setDeletedAt(LocalDateTime.now());
        diagnosisRepository.save(diagnosis);
        return true;
    }

    public CreateDiagnosis updateDiagnosis(CreateDiagnosis createDiagnosis, Long id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnosis not found"));

        if (createDiagnosis != null) {
            Appointment appointment = appointmentRepository.findById(createDiagnosis.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));
            diagnosis.setAppointment(appointment);

            Doctor doctor = doctorRepository.findById(createDiagnosis.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            diagnosis.setDoctor(doctor);

            Patient patient = patientRepository.findById(createDiagnosis.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            diagnosis.setPatient(patient);

            diagnosis.setDiagnosisDetails(createDiagnosis.getDiagnosisDetails());
            diagnosis.setTreatmentPlan(createDiagnosis.getTreatmentPlan());

            diagnosisRepository.save(diagnosis);
        }
        return createDiagnosis;
    }
    public ResponseObject getDiagnosesByPatientId(Long patientId) {
        List<Diagnosis> diagnoses = diagnosisRepository.findByPatientId(patientId);

        if (diagnoses.isEmpty()) {
            throw new RuntimeException("No diagnoses found for the given patient ID");
        }

        // Map the diagnoses to a DTO or return directly (optional)
        List<ViewDiagnosis> diagnosisDTOs = diagnoses.stream().map(diagnosis -> {
            ViewDiagnosis dto = new ViewDiagnosis();
            dto.setId(diagnosis.getId());
            dto.setDiagnosisDetails(diagnosis.getDiagnosisDetails());
            dto.setTreatmentPlan(diagnosis.getTreatmentPlan());
            dto.setDoctorId(diagnosis.getDoctor().getId());
            dto.setAppointmentId(diagnosis.getAppointment().getId());
            dto.setPatientId(diagnosis.getPatient().getId());
            return dto;
        }).toList();

        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(diagnosisDTOs);
        return responseObject;
    }
    public ResponseObject getDiagnosesByDoctorId(Long doctorId) {
        List<Diagnosis> diagnoses = diagnosisRepository.findByDoctorId(doctorId);

        if (diagnoses.isEmpty()) {
            throw new RuntimeException("No diagnoses found for the given doctor ID");
        }

        // Map the diagnoses to a DTO or return directly
        List<ViewDiagnosis> diagnosisDTOs = diagnoses.stream().map(diagnosis -> {
            ViewDiagnosis dto = new ViewDiagnosis();
            dto.setId(diagnosis.getId());
            dto.setDiagnosisDetails(diagnosis.getDiagnosisDetails());
            dto.setTreatmentPlan(diagnosis.getTreatmentPlan());
            dto.setAppointmentId(diagnosis.getAppointment().getId());
            dto.setDoctorId(diagnosis.getDoctor().getId());
            dto.setPatientId(diagnosis.getPatient().getId());
            return dto;
        }).toList();

        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(diagnosisDTOs);
        return responseObject;
    }

}
