package com.example.HospitalManagment.data.doctor;

import com.example.HospitalManagment.data.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDoctor extends BaseDTO {
    private String firstName;
    private String lastName;
    private Long age;
    private String gender;
    private String phoneNumber;
    private String specialization;
    private String qualification;
    private Boolean isActive;
    private String email;
    private Long departmentId;
    private Long cityId;

}