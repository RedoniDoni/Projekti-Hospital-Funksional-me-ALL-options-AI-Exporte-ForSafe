package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.FeedBack.ViewFeedBack;
import com.example.HospitalManagment.data.nurse.FeedBackForDoctorDTO;
import com.example.HospitalManagment.data.nurse.FeedBackForNurseDTO;
import com.example.HospitalManagment.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {

    @Query("select new com.example.HospitalManagment.data.FeedBack.ViewFeedBack(fb.comment, fb.rating, " +
            "n.id, n.firstName,d.id,concat( d.firstName , d.lastName)) " +
            "from FeedBack fb " +
            "left join fb.nurse n " +
            "left join fb.doctor d")
    List<ViewFeedBack> getViewFeedBack();

    @Query("SELECT new com.example.HospitalManagment.data.nurse.FeedBackForNurseDTO(n.id, f.comment, f.rating, n.firstName, n.description, n.category, n.room.roomName) " +
            "FROM FeedBack f " +
            "LEFT JOIN f.nurse n " +
            "WHERE (:search IS NOT NULL AND (CAST(n.id AS string) LIKE %:search% OR n.firstName LIKE %:search%))")
    List<FeedBackForNurseDTO> findByNurseIdOrName(@Param("search") String search);

    @Query("SELECT new com.example.HospitalManagment.data.nurse.FeedBackForDoctorDTO(d.id, f.comment, f.rating, d.firstName,d.lastName,d.phoneNumber) " +
            "FROM FeedBack f " +
            "LEFT JOIN f.doctor d " +
            "WHERE (:search IS NOT NULL AND (CAST(d.id AS string) LIKE %:search% OR d.firstName LIKE %:search%))")
    List<FeedBackForDoctorDTO> findByDoctorIdOrName(@Param("search") String search);

    @Query("SELECT new com.example.HospitalManagment.data.nurse.FeedBackForNurseDTO(" +
            "f.nurse.id, f.comment, f.rating, n.firstName, n.description, n.category, n.room.roomName) " +
            "FROM FeedBack f " +
            "JOIN f.nurse n " +
            "WHERE f.nurse IS NOT NULL")
    List<FeedBackForNurseDTO> findFeedbacksForNurse();

    @Query("SELECT new com.example.HospitalManagment.data.nurse.FeedBackForDoctorDTO(" +
            "f.doctor.id, f.comment, f.rating, d.firstName, d.lastName, d.phoneNumber) " +
            "FROM FeedBack f " +
            "JOIN f.doctor d " +
            "WHERE f.doctor IS NOT NULL")
    List<FeedBackForDoctorDTO> findFeedbacksForDoctor();



}
