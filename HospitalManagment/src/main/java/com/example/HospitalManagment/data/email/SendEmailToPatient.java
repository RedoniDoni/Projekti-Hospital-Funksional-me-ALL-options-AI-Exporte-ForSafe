package com.example.HospitalManagment.data.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailToPatient {
    private Long patientId;
    private String subject;
    private String body;
}
