package com.example.HospitalManagment.data.appointment;

import com.example.HospitalManagment.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointment {

    private Long patientId;

    private Long doctorId;

    private Status status;

    private String reason;
}
