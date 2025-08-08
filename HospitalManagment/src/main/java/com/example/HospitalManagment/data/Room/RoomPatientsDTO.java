package com.example.HospitalManagment.data.Room;

import com.example.HospitalManagment.data.patient.ViewPatient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomPatientsDTO {
    private String roomName;
    private List<ViewPatient> patients;
}
