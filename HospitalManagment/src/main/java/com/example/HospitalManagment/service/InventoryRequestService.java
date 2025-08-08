package com.example.HospitalManagment.service;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.InventoryRequest.InventoryRequestCreate;
import com.example.HospitalManagment.data.InventoryRequest.InventoryRequestView;
import com.example.HospitalManagment.data.InventoryRequest.InventoryRequestViewByEntityDTO;
import com.example.HospitalManagment.entity.InventoryRequest;
import com.example.HospitalManagment.enums.Entity;
import com.example.HospitalManagment.exception.ResourceNotFoundException;
import com.example.HospitalManagment.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryRequestService {

    private InventoryRequestRepository inventoryRequestRepository;

    private DoctorRepository doctorRepository;

    private NurseRepository nurseRepository;

    private PatientRepository patientRepository;

    private InventoryRepository inventoryRepository;


    public InventoryRequestCreate createInventoryRequest(InventoryRequestCreate createDTO) {
        InventoryRequest inventoryRequest = new InventoryRequest();

        // Set data from DTO to entity
        inventoryRequest.setQuantityRequested(createDTO.getQuantityRequested());

        // Fetch related entities by their IDs
        inventoryRequest.setInventory(inventoryRepository.findById(createDTO.getInventoryId()).orElseThrow(() -> new ResourceNotFoundException("Inventory not found")));
        if (createDTO.getDoctorId() != null) {
            inventoryRequest.setDoctor(doctorRepository.findById(createDTO.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("Doctor not found")));
        }
        if (createDTO.getNurseId() != null) {
            inventoryRequest.setNurse(nurseRepository.findById(createDTO.getNurseId()).orElseThrow(() -> new ResourceNotFoundException("Nurse not found")));
        }
        if (createDTO.getPatientId() != null) {
            inventoryRequest.setPatient(patientRepository.findById(createDTO.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient not found")));
        }

         inventoryRequestRepository.save(inventoryRequest);
        return createDTO;
    }

    public ResponseObject getRequestInventoryByEntity(String entity) {
        ResponseObject responseObject = new ResponseObject();
        if(entity.trim().isEmpty()){
            List<InventoryRequestView> allInventoryRequests = inventoryRequestRepository.findAllInventoryRequest();
            responseObject.setData(allInventoryRequests);
            return responseObject;
        }
        List<InventoryRequestViewByEntityDTO> inventorys = List.of();
        if (entity.equalsIgnoreCase(Entity.DOCTOR.name())) {

            inventorys = inventoryRequestRepository.findInventoryRequestByDoctorEntity(entity);
        }
        if (entity.equalsIgnoreCase(Entity.NURSE.name())) {
            inventorys = inventoryRequestRepository.findInventoryRequestByNurseEntity(entity);

        }
        if (entity.equalsIgnoreCase(Entity.PATIENT.name())) {
            inventorys = inventoryRequestRepository.findInventoryRequestByPatientEntity(entity);
        }
        responseObject.setData(inventorys);
        return responseObject;

    }


//    public InventoryRequestViewDTO getInventoryRequestById(Long id) {
//        InventoryRequest inventoryRequest = inventoryRequestRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("InventoryRequest not found for ID: " + id));
//        return convertToViewDTO(inventoryRequest);
//    }


//    public List<InventoryRequestViewDTO> getAllInventoryRequests() {
//        List<InventoryRequest> inventoryRequests = inventoryRequestRepository.findAll();
//        return inventoryRequests.stream().map(this::convertToViewDTO).collect(Collectors.toList());
//    }


    public InventoryRequestCreate updateInventoryRequest(Long id, InventoryRequestCreate createDTO) {
        InventoryRequest existingRequest = inventoryRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InventoryRequest not found for ID: " + id));

        existingRequest.setQuantityRequested(createDTO.getQuantityRequested());
        existingRequest.setInventory(inventoryRepository.findById(createDTO.getInventoryId()).orElseThrow(() -> new ResourceNotFoundException("Inventory not found")));

        if (createDTO.getDoctorId() != null) {
            existingRequest.setDoctor(doctorRepository.findById(createDTO.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("Doctor not found")));
        } else {
            existingRequest.setDoctor(null);
        }

        if (createDTO.getNurseId() != null) {
            existingRequest.setNurse(nurseRepository.findById(createDTO.getNurseId()).orElseThrow(() -> new ResourceNotFoundException("Nurse not found")));
        } else {
            existingRequest.setNurse(null);
        }

        if (createDTO.getPatientId() != null) {
            existingRequest.setPatient(patientRepository.findById(createDTO.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient not found")));
        } else {
            existingRequest.setPatient(null);
        }

        inventoryRequestRepository.save(existingRequest);
        return createDTO;
    }


    public Boolean deleteInventoryRequest(Long id) {
        InventoryRequest inventoryRequest = inventoryRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InventoryRequest not found"));

        return true;
    }

//    public List<InventoryRequestView> getAllInventoryRequests() {
//        return inventoryRepository;
//    }

    public ResponseObject allInventoryRequest(){
        List<InventoryRequest>inventoryRequests = inventoryRequestRepository.findAll();
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(inventoryRequests);
        return responseObject;
    }

    // Helper method to convert entity to DTO
//    private InventoryRequestView convertToViewDTO(InventoryRequest inventoryRequest) {
//        InventoryRequestView viewDTO = new InventoryRequestView();
//        viewDTO.setId(inventoryRequest.getId());
//        viewDTO.setQuantityRequested(inventoryRequest.getQuantityRequested());
//        viewDTO.setInventoryItemName(inventoryRequest.getInventory().getItemName());
//
//        if (inventoryRequest.getDoctor() != null) {
//            viewDTO.setDoctorName(inventoryRequest.getDoctor().getName());
//        }
//        if (inventoryRequest.getNurse() != null) {
//            viewDTO.setNurseName(inventoryRequest.getNurse().getName());
//        }
//        if (inventoryRequest.getPatient() != null) {
//            viewDTO.setPatientName(inventoryRequest.getPatient().getName());
//        }
//
//        return viewDTO;
//    }
}
