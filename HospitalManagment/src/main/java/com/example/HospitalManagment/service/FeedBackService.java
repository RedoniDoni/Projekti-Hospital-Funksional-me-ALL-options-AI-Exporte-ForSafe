package com.example.HospitalManagment.service;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.FeedBack.CreateFeedBack;
import com.example.HospitalManagment.data.FeedBack.ViewFeedBack;
import com.example.HospitalManagment.data.nurse.FeedBackForDoctorDTO;
import com.example.HospitalManagment.data.nurse.FeedBackForNurseDTO;
import com.example.HospitalManagment.entity.*;
import com.example.HospitalManagment.repository.*;
import com.example.HospitalManagment.security.service.JwtService;
import lombok.AllArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedBackService {
    private final FeedBackRepository feedBackRepository;

    private final DoctorRepository doctorRepository;

    private final NurseRepository nurseRepository;

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public ResponseObject getAllFeedBack() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewFeedBack> feedBacks = feedBackRepository.getViewFeedBack();
        responseObject.setData(feedBacks);
        return responseObject;
    }

    public CreateFeedBack addFeedBack(CreateFeedBack createFeedBack) {
        FeedBack feedBack = new FeedBack();
        if (createFeedBack != null) {
            feedBack.setComment(createFeedBack.getComment());
            feedBack.setRating(createFeedBack.getRating());
            User user= userRepository.findById(createFeedBack.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
            feedBack.setUser(user);

            if (createFeedBack.getDoctorId() != null) {
                Optional<Doctor> doctor = doctorRepository.findById(createFeedBack.getDoctorId());
                feedBack.setDoctor(doctor.get());
            } else {
                feedBack.setDoctor(null);
            }
            if (createFeedBack.getNurseId() != null) {
                Optional<Nurse> nurse = nurseRepository.findById(createFeedBack.getNurseId());
                feedBack.setNurse(nurse.get());
            } else {
                feedBack.setNurse(null);
            }
//TODO now we can not get currentUser in this form because we need to send token in all request
            // User user= UserDetails;
//            User user = jwtService.getCurrentUser();
//            feedBack.setUser(user);

            feedBackRepository.save(feedBack);
        }
        return createFeedBack;
    }
    public List<FeedBackForNurseDTO> getFeedbackByNurseIdOrName(String searchParam) {
        String search = searchParam.toLowerCase();
        if (!StringUtils.hasText(search)) {
            search = null;
        }
        return feedBackRepository.findByNurseIdOrName(search);
    }

    public List<FeedBackForDoctorDTO> getFeedbackByDoctorIdOrName(String searchParam) {
        String search = searchParam.toLowerCase();
        if (!StringUtils.hasText(search)) {
            search = null;
        }
        return feedBackRepository.findByDoctorIdOrName(search);
    }
    public boolean deletedFeedBack(Long id){
        FeedBack feedBack = feedBackRepository.findById(id).orElseThrow(()->new RuntimeException("Error"));
        feedBack.setDeletedAt(LocalDateTime.now());
        feedBackRepository.save(feedBack);
        return true;

    }


    public List<FeedBackForNurseDTO> getFeedbacksForNurse() {
        return feedBackRepository.findFeedbacksForNurse();
    }

    public List<FeedBackForDoctorDTO> getFeedbacksForDoctor() {
        return feedBackRepository.findFeedbacksForDoctor();
    }

}
