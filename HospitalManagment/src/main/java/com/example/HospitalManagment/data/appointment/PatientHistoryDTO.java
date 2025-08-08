package com.example.HospitalManagment.data.appointment;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientHistoryDTO {

    private Long patientId;
    private String patientFirstName;
    private String patientLastName;

    private List<AppointmentDTO> appointments;
    private List<DiagnosisDTO> diagnoses;

}
