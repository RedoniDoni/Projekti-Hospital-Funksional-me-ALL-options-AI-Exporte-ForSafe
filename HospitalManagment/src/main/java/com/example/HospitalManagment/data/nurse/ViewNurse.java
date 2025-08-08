package com.example.HospitalManagment.data.nurse;

import com.example.HospitalManagment.entity.Room;
import com.example.HospitalManagment.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewNurse {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String description;

    private String street;

    private Category category;
    private String phoneNumber;

    private String roomName;

    private String departmentName;

    private String cityName;

    private Long departmentId;

    private Long roomId;

    private Long cityId;





}
