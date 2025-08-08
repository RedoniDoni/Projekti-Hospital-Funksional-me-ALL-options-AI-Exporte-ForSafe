package com.example.HospitalManagment.data.Room;

import com.example.HospitalManagment.data.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoom extends BaseDTO {
    private String roomName;
    private String description;
    private Long floor;
    private Long nrOfBeds;
    private Long departamentId;

}
