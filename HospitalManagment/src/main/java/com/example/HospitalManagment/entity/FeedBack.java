package com.example.HospitalManagment.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class FeedBack extends DataEntity{

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
