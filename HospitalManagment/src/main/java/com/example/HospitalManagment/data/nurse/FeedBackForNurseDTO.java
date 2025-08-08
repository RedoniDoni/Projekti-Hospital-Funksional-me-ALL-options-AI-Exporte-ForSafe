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
public class FeedBackForNurseDTO {

    private Long nurseId;

    private String comment;

    private Long rating;

    private String NurseFirstName;

    private String NurseDescription;

    private Category NurseCategory;

    private String NurseRoomName;
}
