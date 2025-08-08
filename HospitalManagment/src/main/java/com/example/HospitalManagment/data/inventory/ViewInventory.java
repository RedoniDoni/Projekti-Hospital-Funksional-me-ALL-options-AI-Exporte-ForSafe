package com.example.HospitalManagment.data.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewInventory {
    private Long id;
    private String article;
    private String description;
    private Long quantity;
    private Double totalPrice;
}
