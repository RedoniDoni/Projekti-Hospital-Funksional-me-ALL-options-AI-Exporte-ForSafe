package com.example.HospitalManagment.data.patient;

import com.example.HospitalManagment.data.appointment.AppointmentDTO;
import com.example.HospitalManagment.data.appointment.DiagnosisDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewPatient {
    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private Long age;
    private String city;
    private LocalDateTime createdAt;
    private String roomName;
    private String cityName;

    private List<AppointmentDTO> appointments;
    private List<DiagnosisDTO> diagnoses;

    public ViewPatient(Long id,String firstName,String lastName,String street,String phoneNumber,String email,String dateOfBirth,Long age,String city,LocalDateTime createdAt,String roomName,String cityName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.city = city;
        this.createdAt = createdAt;
        this.roomName = roomName;
        this.cityName = cityName;

    }
    public ViewPatient(Long id,String firstName,String lastName,String street,String phoneNumber,String email,String dateOfBirth,Long age){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = age;


    }
}
