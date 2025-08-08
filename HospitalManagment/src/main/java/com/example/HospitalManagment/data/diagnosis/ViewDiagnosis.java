package com.example.HospitalManagment.data.diagnosis;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewDiagnosis {
    private Long id;
    private String diagnosisDetails;
    private String treatmentPlan;
    private Long appointmentId;
    private Long doctorId;
    private Long patientId;
}
