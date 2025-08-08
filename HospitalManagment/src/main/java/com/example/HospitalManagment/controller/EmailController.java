package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.data.email.SendEmailDTO;
import com.example.HospitalManagment.data.email.SendEmailToDoctor;
import com.example.HospitalManagment.data.email.SendEmailToNurse;
import com.example.HospitalManagment.data.email.SendEmailToPatient;
import com.example.HospitalManagment.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("${base.url}/email")
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody SendEmailDTO sendEmailDTO) {
        emailService.sendSimpleEmail(sendEmailDTO);
        return "Email sent successfully!";
    }

    @PostMapping("/sendEmail/patient")
    public String sendEmailToPatient(@RequestBody SendEmailToPatient sendEmailDTO) {
        emailService.sendEmailToPatientWithId(sendEmailDTO);
        return "Email sent successfully! to patient with id "+ sendEmailDTO.getPatientId();
    }



    @PostMapping("/sendEmail/doctor")
    public String sendEmailToDoctor(@RequestBody SendEmailToDoctor sendEmailDTO) {
        emailService.sendEmailToDoctorWithId(sendEmailDTO);
        return "Email sent successfully! to doctor with id "+ sendEmailDTO.getDoctorId();
    }

//    @GetMapping("/sendPatientWelcome")
//    public void sendPatientWelcome() throws MessagingException, IOException {
//        emailService.sendWelcomeEmailToPatient();
//    }

    @PostMapping("/sendEmail/nurse")
    public String sendEmailToNurse(@RequestBody SendEmailToNurse sendEmailDTO) {
        emailService.sendEmailToNurseWithId(sendEmailDTO);
        return "Email sent successfully! to patient with id "+ sendEmailDTO.getNurseId();
    }
}