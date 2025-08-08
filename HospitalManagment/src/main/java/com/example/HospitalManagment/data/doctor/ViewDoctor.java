package com.example.HospitalManagment.data.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewDoctor {
    private Long doctorId;
    private String firstName;
    private String lastName;
    private Long age;
    private String gender;
    private String phoneNumber;
    private String specialization;
    private String qualification;
    private Boolean isActive;
    private String departamentName;
    private String CityName;
}
