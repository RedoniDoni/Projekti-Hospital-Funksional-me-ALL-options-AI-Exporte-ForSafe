package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.InventoryRequest.InventoryRequestCreate;
import com.example.HospitalManagment.service.InventoryRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("${base.url}/inventory-requests")
@AllArgsConstructor
public class InventoryRequestController {

    private InventoryRequestService inventoryRequestService;

    // Create Inventory Request
    @PostMapping
    public ResponseEntity<InventoryRequestCreate> createInventoryRequest(@RequestBody InventoryRequestCreate inventoryRequestCreateDTO) {
        InventoryRequestCreate newRequest = inventoryRequestService.createInventoryRequest(inventoryRequestCreateDTO);
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }

    @GetMapping("/by-entity")
    public ResponseEntity<?> getAll(@RequestParam(value = "entity", required = false) String entity) {
        String methodName = "get All inventory";
        log.info("Executing {}", methodName);
        ResponseObject responseObject;
            // Logic when entity is provided
            responseObject = inventoryRequestService.getRequestInventoryByEntity(entity);
        responseObject.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


    // Get Inventory Request by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<InventoryRequestViewDTO> getInventoryRequestById(@PathVariable Long id) {
//        InventoryRequestViewDTO inventoryRequest = inventoryRequestService.getInventoryRequestById(id);
//        return new ResponseEntity<>(inventoryRequest, HttpStatus.OK);
//    }
//
//    // Get All Inventory Requests
//    @GetMapping
//    public ResponseEntity<List<InventoryRequestViewDTO>> getAllInventoryRequests() {
//        List<InventoryRequestViewDTO> inventoryRequests = inventoryRequestService.getAllInventoryRequests();
//        return new ResponseEntity<>(inventoryRequests, HttpStatus.OK);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InventoryRequestCreate> updateInventoryRequest(@PathVariable Long id, @RequestBody InventoryRequestCreate inventoryRequestCreateDTO) {
        InventoryRequestCreate updatedRequest = inventoryRequestService.updateInventoryRequest(id, inventoryRequestCreateDTO);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryRequest(@PathVariable Long id) {
        inventoryRequestService.deleteInventoryRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}