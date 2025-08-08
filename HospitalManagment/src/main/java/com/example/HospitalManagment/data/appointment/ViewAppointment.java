package com.example.HospitalManagment.data.appointment;

import com.example.HospitalManagment.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewAppointment {

    private Long appointmentId;

    private Long patientId;

    private Long doctorId;

    private LocalDateTime data;

    private Status status;

    private String reason;

    private String doctorName;

    private String patientName;

    public ViewAppointment(Long appointmentId,LocalDateTime data,String reason,Status status,Long doctorId,String doctorName,Long patientId,String patientName){
        this.appointmentId = appointmentId;
        this.data = data;
        this.reason = reason;
        this.status = status;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
    }
    public ViewAppointment(Long appointmentId,Long patientId,Long doctorId,LocalDateTime data,Status status,String reason){
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.data = data;
        this.status = status;
        this.reason = reason;
    }
}
