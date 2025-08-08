package com.example.HospitalManagment.data.vacation;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVacation {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String reason;

    private String certification;

    private Long DoctorId;

    private Long NurseId;
}
