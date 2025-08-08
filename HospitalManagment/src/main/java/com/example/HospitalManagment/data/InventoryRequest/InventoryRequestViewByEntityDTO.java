package com.example.HospitalManagment.data.InventoryRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestViewByEntityDTO {
    private Long quantityRequested;
    private Long entityId;
    private String article;
    private String entityName;
    private String description;
    private Long quantity;
    private Double totalPrice;
   // private List<InventoryRequest> requests;


}
