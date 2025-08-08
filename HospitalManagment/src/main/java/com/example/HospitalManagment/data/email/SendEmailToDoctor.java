package com.example.HospitalManagment.data.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailToDoctor {
    private Long doctorId;
    private String subject;
    private String body;
}
