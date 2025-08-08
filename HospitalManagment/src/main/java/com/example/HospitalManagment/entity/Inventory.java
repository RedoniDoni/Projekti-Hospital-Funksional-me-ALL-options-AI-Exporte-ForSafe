package com.example.HospitalManagment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory extends DataEntity{

    @Column(name = "article")
    private String article;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "inventory")
    private List<InventoryRequest> requests;

}
