package com.example.HospitalManagment.data.nurse;

import com.example.HospitalManagment.data.BaseDTO;
import com.example.HospitalManagment.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateNurse extends BaseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String phoneNumber;


    private String description;

    private Category category;

    private Long roomId;

    private Long cityId;

    private Long departmentId;
}