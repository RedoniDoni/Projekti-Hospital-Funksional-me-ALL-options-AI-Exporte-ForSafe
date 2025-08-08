package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.inventory.ViewInventory;
import com.example.HospitalManagment.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("select  new com.example.HospitalManagment.data.inventory.ViewInventory (i.id,i.article,i.description,i.quantity,i.totalPrice) from Inventory i where i.deletedAt is null")
    List<ViewInventory> findAllInventory();


    @Query("select  new com.example.HospitalManagment.data.inventory.ViewInventory " +
            "(i.id,i.article,i.description,i.quantity,i.totalPrice)" +
            " from Inventory i where i.id =:id and  i.deletedAt is null")
    Optional<ViewInventory> findInventoryById(Long id);
}
