package com.example.HospitalManagment.data;

import com.example.HospitalManagment.enums.Category;
import com.example.HospitalManagment.enums.Entity;
import com.example.HospitalManagment.enums.Status;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestForAllEntityDTO {
    private String firstName;
    private String lastName;

    @NotBlank(message = "must.not.be.empty")
    @Email(message = "email.is.not.valid")
    private String email;

    @Size(min = 5, max =45, message = "password.min.max")
    private String password;

    private String phoneNumber;
    private String imageUrl;
    private Status status;
    private Entity role;

    //for doctor
    private Long age;
    private String gender;
    private String specialization;
    private String qualification;
    private Boolean isActive;
    private Long departmentId;
    private Long cityId;

    //for nurse
    private String description;
    private Category category;
    private Long roomId;

    //for patient
    private String street;
    private String dateOfBirth;

}
