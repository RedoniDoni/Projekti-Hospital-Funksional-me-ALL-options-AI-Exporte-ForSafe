package com.example.HospitalManagment.data.InventoryRequest;

import com.example.HospitalManagment.data.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestView extends BaseDTO {
    private Long quantityRequested;
    private Long inventoryId;
    private Long doctorId;
    private Long nurseId;
    private Long patientId;
}
