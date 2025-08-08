package com.example.HospitalManagment.repository;

import com.example.HospitalManagment.data.vacation.ViewVacation;
import com.example.HospitalManagment.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

    @Query("select new com.example.HospitalManagment.data.vacation.ViewVacation(v.startDate,v.endDate,v.reason,v.certification,n.id,d.id )" +
            "from Vacation v " +
            "left join Nurse n on n.id=v.nurse.id " +
            "left join Doctor d on d.id=v.doctor.id " +
            "where v.deletedAt is null ")
    List<ViewVacation> getAllVacations();

    @Query("select new com.example.HospitalManagment.data.vacation.ViewVacation(v.startDate,v.endDate,v.reason,v.certification,v.doctor.id)" +
            "from Vacation v where " +
            "v.deletedAt is null and v.doctor.id is not NULL")
    List<ViewVacation> getAllVacationsByDoctor();

    @Query("select new com.example.HospitalManagment.data.vacation.ViewVacation(v.startDate,v.endDate,v.reason,v.certification,v.nurse.id)" +
            "from Vacation v where " +
            "v.deletedAt is null and v.nurse.id is not null")
    List<ViewVacation> getAllVacationsByNurse();

}
