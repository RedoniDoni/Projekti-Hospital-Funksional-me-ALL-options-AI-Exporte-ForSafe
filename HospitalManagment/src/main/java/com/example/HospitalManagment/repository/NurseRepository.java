package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.nurse.ViewNurse;
import com.example.HospitalManagment.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    @Query("select new com.example.HospitalManagment.data.nurse.ViewNurse(n.id,n.firstName,n.lastName,n.email,n.description,n.street,n.category,n.phoneNumber,r.roomName,n.department.departmentName,n.city.name,n.department.id,n.room.id,n.city.id)" +
            "from Nurse n" +
            " left join Room r on n.room.id =r.id " +
            "where n.deletedAt is null ")
    List<ViewNurse> viewAllNurses();

    @Query("select new com.example.HospitalManagment.data.nurse.ViewNurse(n.id, n.firstName, n.lastName, n.email, n.description, n.street, n.category, n.phoneNumber, r.roomName, n.department.departmentName, n.city.name,n.department.id,n.room.id,n.city.id) " +
            "from Nurse n " +
            "left join Room r on n.room.id = r.id " +
            "where n.id = :id and n.deletedAt is null")
    Optional<ViewNurse> findViewNurseById(Long id);

    @Query("SELECT COUNT(n) FROM Nurse n WHERE n.deletedAt IS  NULL")
    long countSoftDeletedNurses();

    @Query("SELECT u from Nurse u where u.email=:email and u.deletedAt is null ")
    Optional<Nurse> findByEmail(@Param("email") String email);
}
