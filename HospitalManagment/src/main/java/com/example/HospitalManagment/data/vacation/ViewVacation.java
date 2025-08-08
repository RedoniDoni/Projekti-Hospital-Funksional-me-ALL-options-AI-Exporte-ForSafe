package com.example.HospitalManagment.data.vacation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ViewVacation {
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String reason;

    private String certification;

    private Long DoctorId;

    private Long NurseId;
    public ViewVacation (LocalDateTime startDate, LocalDateTime endDate,String reason, String certification, Long DoctorId,Long NurseId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.certification = certification;
        this.DoctorId = DoctorId;
        this.NurseId = NurseId;
    }
    public ViewVacation (LocalDateTime startDate, LocalDateTime endDate,String reason, String certification, Long DoctorId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.certification = certification;
        this.DoctorId = DoctorId;
    }
}
