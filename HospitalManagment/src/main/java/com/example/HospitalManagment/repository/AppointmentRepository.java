package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.appointment.AppointmentDTO;
import com.example.HospitalManagment.data.appointment.ViewAppointment;
import com.example.HospitalManagment.data.nurse.ViewNurse;
import com.example.HospitalManagment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select new com.example.HospitalManagment.data.appointment.ViewAppointment(a.id,a.patient.id,a.doctor.id,a.date,a.status,a.reason)" +
            "from Appointment a" +
            " left join Doctor d on a.doctor.id =d.id " +
            "where a.deletedAt is null ")
    List<ViewAppointment> viewAllAppointments();

    @Query("SELECT new com.example.HospitalManagment.data.appointment.AppointmentDTO(" +
            "a.id, a.reason, a.date, CONCAT(d.firstName, ' ', d.lastName), a.status) " +
            "FROM Appointment a " +
            "JOIN a.doctor d " +
            "WHERE a.patient.id = :patientId AND a.deletedAt IS NULL")
    List<AppointmentDTO> findAppointmentsByPatientId(@Param("patientId") Long patientId);

    List<Appointment> findAllByPatientId(Long patientId);

    @Query("SELECT new com.example.HospitalManagment.data.appointment.ViewAppointment(a.id, a.date, a.reason, a.status, d.id, d.firstName, p.id, p.firstName) " +
            "FROM Appointment a " +
            "JOIN a.doctor d " +
            "JOIN a.patient p " +
            "WHERE d.id = :doctorId AND a.deletedAt IS NULL")
    List<ViewAppointment> viewAppointmentsByDoctorId(@Param("doctorId") Long doctorId);



}
