package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.Room.ViewRoom;
import com.example.HospitalManagment.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select new com.example.HospitalManagment.data.Room.ViewRoom (r.id,r.roomName,r.description,r.floor,r.nrOfBeds,d.departmentName)" +
            " from Room r " +
            "left join Department d on r.departament.id=d.id " +
            "where r.deletedAt is null")
    List<ViewRoom> findAllRooms();

    @Query("select new com.example.HospitalManagment.data.Room.ViewRoom (r.id,r.roomName,r.description,r.floor,r.nrOfBeds,d.departmentName)" +
            " from Room r " +
            "left join Department d on r.departament.id=d.id " +
            "where r.id = :id and r.deletedAt is null")
    Optional<ViewRoom> findViewPatientById(Long id);
}
