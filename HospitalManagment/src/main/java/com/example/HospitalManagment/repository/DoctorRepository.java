package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.doctor.ViewDoctor;
import com.example.HospitalManagment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("select new com.example.HospitalManagment.data.doctor.ViewDoctor (d.id,d.firstName,d.lastName,d.age,d.gender,d.phoneNumber,d.specialization,d.qualification,d.isActive,de.departmentName,c.name) " +
            "from Doctor d " +
            "left join Department de on d.departament.id = de.id " +
            "left join City c on d.city.id = c.id" +
            " where d.deletedAt is null")
    List<ViewDoctor> getAllDoctors();

    @Query("SELECT COUNT(d) FROM Doctor d WHERE d.deletedAt IS  NULL")
    long countSoftDeletedDoctors();

    @Query("SELECT u from Doctor u where u.email=:email and u.deletedAt is null ")
    Optional<Doctor> findByEmail(@Param("email") String email);
}
