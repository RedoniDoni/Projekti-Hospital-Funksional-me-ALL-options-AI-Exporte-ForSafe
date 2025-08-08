package com.example.HospitalManagment.data.diagnosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiagnosis {
    private Long appointmentId;
    private Long doctorId;
    private Long patientId;
    private String diagnosisDetails;
    private String treatmentPlan;
}
