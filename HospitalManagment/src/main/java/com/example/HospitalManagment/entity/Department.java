package com.example.HospitalManagment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department extends DataEntity{

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "description")
    private String description;

    @Column(name = "department_size")
    private Long departamentSize;

    @Column(name = "department_status")
    private String departamentStatus;


}
