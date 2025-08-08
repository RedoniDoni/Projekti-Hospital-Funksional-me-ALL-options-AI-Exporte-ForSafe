package com.example.HospitalManagment.data.appointment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class DiagnosisDTO {
    private Long diagnosisId;
    private String diagnosisDetails;
    private String treatmentPlan;
    private String doctorName;
}