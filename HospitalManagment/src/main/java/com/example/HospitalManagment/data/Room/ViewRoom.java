package com.example.HospitalManagment.data.Room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewRoom {

    private Long id;
    private String roomName;
    private String description;
    private Long floor;
    private Long nrOfBeds;
    private String departament;
}
