package com.example.HospitalManagment.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BaseDTO {

    private Long id;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String updatedBy;
    @CreatedBy
    private String createdBy;

    public BaseDTO(Long id, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
