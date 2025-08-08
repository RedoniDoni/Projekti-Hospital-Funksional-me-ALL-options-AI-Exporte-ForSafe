package com.example.HospitalManagment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "vacation")
public class Vacation extends DataEntity{

    @Column(name = "start_date")
    private LocalDateTime startDate ;

    @Column(name = "end_date")
    private LocalDateTime endDate ;

    @Column(name = "reason")
    private String reason;

    @Column(name="certification")
    private String certification ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id",nullable = true)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id",nullable = true)
    private Nurse nurse ;
}
