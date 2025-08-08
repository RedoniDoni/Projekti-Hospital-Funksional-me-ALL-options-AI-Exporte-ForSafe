package com.example.HospitalManagment.data.user;

import com.example.HospitalManagment.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Status status;
    private String role;




    public UserDto(Long id, String firstName,String lastName,String email,String phoneNumber,Status status,String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName=lastName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.status=status;
        this.role = role;

    }
}