package com.example.HospitalManagment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permissions extends DataEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private List<RolePermissions> rolePermissions;
}
