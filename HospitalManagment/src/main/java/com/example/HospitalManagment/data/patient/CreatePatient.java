package com.example.HospitalManagment.data.patient;

import com.example.HospitalManagment.data.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatient extends BaseDTO {

    private String firstName;
    private String lastName;
    private String street;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private Long age;
    private Long cityId;
    private Long roomId;

}
