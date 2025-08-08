package com.example.HospitalManagment.data.nurse;

import com.example.HospitalManagment.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackForDoctorDTO {

    private Long doctorId;

    private String comment;

    private Long rating;

    private String doctorFirstName;

    private String doctorLastName;

    private String doctorPhoneNumber;

}
