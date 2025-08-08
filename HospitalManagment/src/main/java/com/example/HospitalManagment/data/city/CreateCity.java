package com.example.HospitalManagment.data.city;

import com.example.HospitalManagment.data.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCity extends BaseDTO {
    private String name;
}
