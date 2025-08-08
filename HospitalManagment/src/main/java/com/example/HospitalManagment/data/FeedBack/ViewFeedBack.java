package com.example.HospitalManagment.data.FeedBack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewFeedBack {

    private String comment;
    private Long rating;

    private Long nurseId;
    private String nurseName;

    private Long DoctorId;
    private String DoctorName;
}
