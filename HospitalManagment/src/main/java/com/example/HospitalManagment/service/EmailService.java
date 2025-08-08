package com.example.HospitalManagment.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.HospitalManagment.data.email.*;
import com.example.HospitalManagment.entity.Doctor;
import com.example.HospitalManagment.entity.Nurse;
import com.example.HospitalManagment.entity.Patient;
import com.example.HospitalManagment.repository.DoctorRepository;
import com.example.HospitalManagment.repository.NurseRepository;
import com.example.HospitalManagment.repository.PatientRepository;
import com.example.HospitalManagment.util.TemplateUtil;
import com.example.HospitalManagment.util.TemplateWildcards;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final TemplateUtil templateUtil;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private NurseRepository nurseRepository;

    public void sendEmailToPatientWithId(SendEmailToPatient sendEmailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        Optional<Patient> patient =patientRepository.findById(sendEmailDTO.getPatientId());
        if(patient.isEmpty()) {
            throw new RuntimeException("Patient not found with id " + sendEmailDTO.getPatientId());
        }
        message.setTo(patient.get().getEmail());
        message.setSubject(sendEmailDTO.getSubject());
        message.setText(sendEmailDTO.getBody());

        mailSender.send(message);
    }

    public void sendEmailToDoctorWithId(SendEmailToDoctor sendEmailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        Optional<Doctor> doctor =doctorRepository.findById(sendEmailDTO.getDoctorId());
        if(doctor.isEmpty()) {
            throw new RuntimeException("Doctor not found with id " + sendEmailDTO.getDoctorId());
        }
        message.setTo(doctor.get().getEmail());
        message.setSubject(sendEmailDTO.getSubject());
        message.setText(sendEmailDTO.getBody());

        mailSender.send(message);
    }

    public void sendEmailToNurseWithId(SendEmailToNurse sendEmailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        Optional<Nurse> nurse =nurseRepository.findById(sendEmailDTO.getNurseId());
        if(nurse.isPresent()) {
            throw new RuntimeException("Nurse not found with id " + sendEmailDTO.getNurseId());
        }
        message.setTo(nurse.get().getEmail());
        message.setSubject(sendEmailDTO.getSubject());
        message.setText(sendEmailDTO.getBody());

        mailSender.send(message);
    }



    public void sendSimpleEmail(SendEmailDTO sendEmailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendEmailDTO.getTo());
        message.setSubject(sendEmailDTO.getSubject());
        message.setText(sendEmailDTO.getBody());

        mailSender.send(message);
    }

    public void sendWelcomeEmailToPatient(Long patientId) throws MessagingException, IOException {

        Patient patient =patientRepository.findById(patientId).orElseThrow(()->new NotFoundException("Patient Not Found with this id : "+patientId));
        Map<String, String> variables = replacePatientFields(patient);

        String subjectTemplate = "Create Patient ";
        String bodyTemplatePath = "src/main/resources/templates/WelcomePatientEmailTemplate.html";
        String bodyTemplate = new String(Files.readAllBytes(Paths.get(bodyTemplatePath)), StandardCharsets.UTF_8);

        ReplacedWildCardsDTO replacedWildCardsDTO = templateUtil.getReplacedWildCards(variables, subjectTemplate, bodyTemplate);

        // Create and send the email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(patient.getEmail());
        helper.setSubject(replacedWildCardsDTO.getSubject());
        helper.setText(replacedWildCardsDTO.getBody(), true);


        mailSender.send(message);
    }
    public void sendWelcomeEmailToNurse(Long nurseId) throws MessagingException, IOException {

        Nurse nurse =nurseRepository.findById(nurseId).orElseThrow(()->new NotFoundException("Nurse Not Found with this id : "+nurseId));
        Map<String, String> variables = replaceNurseFields(nurse);

        String subjectTemplate = "Create Nurse ";
        String bodyTemplatePath = "src/main/resources/templates/NurseWelcomeEmailTemplate.html";
        String bodyTemplate = new String(Files.readAllBytes(Paths.get(bodyTemplatePath)), StandardCharsets.UTF_8);

        ReplacedWildCardsDTO replacedWildCardsDTO = templateUtil.getReplacedWildCards(variables, subjectTemplate, bodyTemplate);

        // Create and send the email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(nurse.getEmail());
        helper.setSubject(replacedWildCardsDTO.getSubject());
        helper.setText(replacedWildCardsDTO.getBody(), true);


        mailSender.send(message);
    }

    public void sendWelcomeEmailToDoctor(Long doctorID) throws MessagingException, IOException {

        Doctor doctor =doctorRepository.findById(doctorID).orElseThrow(()->new NotFoundException("Doctor Not Found with this id : "+doctorID));
        Map<String, String> variables = replaceDoctorFields(doctor);

        String subjectTemplate = "Create Docctor ";
        String bodyTemplatePath = "src/main/resources/templates/DoctorWelcomeEmailTemplate.html";
        String bodyTemplate = new String(Files.readAllBytes(Paths.get(bodyTemplatePath)), StandardCharsets.UTF_8);

        ReplacedWildCardsDTO replacedWildCardsDTO = templateUtil.getReplacedWildCards(variables, subjectTemplate, bodyTemplate);

        // Create and send the email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(doctor.getEmail());
        helper.setSubject(replacedWildCardsDTO.getSubject());
        helper.setText(replacedWildCardsDTO.getBody(), true);


        mailSender.send(message);
    }

    private static Map<String, String> replacePatientFields(Patient patient) {
        String cityName= patient.getCity().getName();
        Map<String, String> variables = new HashMap<>();

        variables.put(TemplateWildcards.PATIENT_NAME, patient.getFirstName());
        variables.put(TemplateWildcards.PATIENT_PHONE_NUMBER, patient.getPhoneNumber());
        variables.put(TemplateWildcards.PATIENT_ID, patient.getId().toString());
        variables.put(TemplateWildcards.PATIENT_EMAIL, patient.getEmail());
        variables.put(TemplateWildcards.PATIENT_ADDRESS, patient.getStreet() +" "+cityName);
        variables.put(TemplateWildcards.PATIENT_BIRTH, patient.getDateOfBirth());
        variables.put(TemplateWildcards.PATIENT_AGE, patient.getAge().toString());
        variables.put(TemplateWildcards.PATIENT_ROOM_ID,patient.getRoom().getId().toString());
        variables.put(TemplateWildcards.PATIENT_ROOM_NAME,patient.getRoom().getRoomName());

        return variables;
    }

    private static Map<String, String> replaceNurseFields(Nurse nurse) {
        Map<String, String> variables = new HashMap<>();

        variables.put(TemplateWildcards.NURSE_NAME, nurse.getFirstName());
        variables.put(TemplateWildcards.NURSE_DEPARTAMENT, nurse.getDepartment().getDepartmentName());
        variables.put(TemplateWildcards.NURSE_ID, nurse.getId().toString());
        variables.put(TemplateWildcards.NURSE_SHIFT_TIMING, nurse.getShiftTiming());
        variables.put(TemplateWildcards.NURSE_CONTACT, nurse.getPhoneNumber());
        variables.put(TemplateWildcards.NURSE_ADDRESS, nurse.getStreet());

        return variables;
    }

    private static Map<String, String> replaceDoctorFields(Doctor doctor) {
        Map<String, String> variables = new HashMap<>();

        variables.put(TemplateWildcards.DOCTOR_NAME, doctor.getFirstName());
        variables.put(TemplateWildcards.DOCTOR_DEPARTAMENT, doctor.getDepartament().getDepartmentName());
        variables.put(TemplateWildcards.DOCTOR_SPECIALIZATION, doctor.getSpecialization());
        variables.put(TemplateWildcards.DOCTOR_ID, doctor.getId().toString());
        variables.put(TemplateWildcards.DOCTOR_CONTACT,doctor.getPhoneNumber());
        variables.put(TemplateWildcards.DOCTOR_ADDRESS, doctor.getCity().getName());

        return variables;
    }
}
