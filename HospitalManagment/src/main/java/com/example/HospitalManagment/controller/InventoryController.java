package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.inventory.CreateInventory;
import com.example.HospitalManagment.entity.Inventory;
import com.example.HospitalManagment.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("${base.url}/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity createInventory(@RequestBody CreateInventory inventory) {
        CreateInventory createInventory = inventoryService.createInventory(inventory);

        return ResponseEntity.status(HttpStatus.CREATED).body(createInventory);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInventory(@RequestBody CreateInventory updateInventory, @PathVariable("id") Long id){
        String methodName = "updateInventory";
        CreateInventory updateInventory1=inventoryService.updateInventory(id,updateInventory);
        log.info("executing {}" + methodName);
        return ResponseEntity.status(HttpStatus.OK).body(updateInventory1);
    }

    @DeleteMapping("/deleted/{id}")
    public Boolean deleteInventory(@PathVariable Long id) {
        String methodName="deleteInventory";
        inventoryService.deleteInventory(id);
        log.info("executing {}" + methodName);
        return Boolean.TRUE;
    }
    @GetMapping("/all")
    public ResponseEntity getAll() {
        String methodName="get Alll inventory";
        log.info("executing {}" + methodName);
        ResponseObject responseObject=inventoryService.getInventory();

        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getInventoryById(@PathVariable Long id) {
        String methodName = "getNurseById";
        log.info("executing {}", methodName);
        ResponseObject responseObject = inventoryService.getInventoryById(id);
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }
}
