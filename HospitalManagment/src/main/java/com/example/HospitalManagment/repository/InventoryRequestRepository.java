package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.InventoryRequest.InventoryRequestView;
import com.example.HospitalManagment.data.InventoryRequest.InventoryRequestViewByEntityDTO;
import com.example.HospitalManagment.entity.InventoryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface InventoryRequestRepository extends JpaRepository<InventoryRequest,Long> {

    @Query("select new com.example.HospitalManagment.data.InventoryRequest.InventoryRequestView (ir.quantityRequested,ir.id,d.id,n.id,p.id) " +
            "from InventoryRequest  ir " +
            "left join Doctor d on ir.doctor.id =d.id " +
            "left join Patient p on ir.patient.id=p.id " +
            "left join Nurse n on ir.nurse.id=n.id")
    List<InventoryRequestView> findAllInventoryRequest();

    @Query("select new com.example.HospitalManagment.data.InventoryRequest.InventoryRequestViewByEntityDTO (ir.quantityRequested,d.id,i.article,d.firstName,i.description,i.quantity,i.totalPrice) " +
            "from InventoryRequest  ir " +
            "left join Doctor d on ir.doctor.id =d.id " +
            "left join Inventory i on ir.inventory.id=i.id " +
            "where ir.deletedAt is null")
    List<InventoryRequestViewByEntityDTO> findInventoryRequestByDoctorEntity(@RequestParam ("entity")String entity);

    @Query("select new com.example.HospitalManagment.data.InventoryRequest.InventoryRequestViewByEntityDTO (ir.quantityRequested,n.id,i.article,n.firstName,i.description,i.quantity,i.totalPrice) " +
            "from InventoryRequest  ir " +
            "left join Nurse n on ir.nurse.id=n.id " +
            "left join Inventory i on ir.inventory.id=i.id ")
    List<InventoryRequestViewByEntityDTO> findInventoryRequestByNurseEntity(@RequestParam ("entity")String entity);

    @Query("select new com.example.HospitalManagment.data.InventoryRequest.InventoryRequestViewByEntityDTO (ir.quantityRequested,p.id,i.article,p.firstName,i.description,i.quantity,i.totalPrice) " +
            "from InventoryRequest  ir " +
            "left join Patient p on ir.patient.id=p.id " +
            "left join Inventory i on ir.inventory.id=i.id ")
    List<InventoryRequestViewByEntityDTO> findInventoryRequestByPatientEntity(@RequestParam ("entity")String entity);
}
