package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.data.FeedBack.CreateFeedBack;
import com.example.HospitalManagment.data.nurse.FeedBackForDoctorDTO;
import com.example.HospitalManagment.data.nurse.FeedBackForNurseDTO;
import com.example.HospitalManagment.service.FeedBackService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("${base.url}/feed-back")
@AllArgsConstructor
public class FeedBackController {
    private final FeedBackService feedBackService;

    @PostMapping("/create")
    public ResponseEntity <CreateFeedBack> createFeedBack(@RequestBody @Valid CreateFeedBack createFeedBack) {

        CreateFeedBack createFeedBack1 = feedBackService.addFeedBack(createFeedBack);
        return ResponseEntity.status(HttpStatus.CREATED).body(createFeedBack1);
    }

    @GetMapping("/nurse/search")
    public List<FeedBackForNurseDTO> searchFeedbackByNurse(@RequestParam String search) {
        return feedBackService.getFeedbackByNurseIdOrName(search);
    }

    @GetMapping("/doctor/search")
    public List<FeedBackForDoctorDTO> searchFeedbackByDoctor(@RequestParam String search) {
        return feedBackService.getFeedbackByDoctorIdOrName(search);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        String methodName = "delete";
        log.info("{} -> Delete FeedBack", methodName);
        feedBackService.deletedFeedBack(id);
        return Boolean.TRUE;
    }

    @GetMapping("/forNurse")
    public ResponseEntity<List<FeedBackForNurseDTO>> getFeedbacksForNurse() {
        List<FeedBackForNurseDTO> feedbacks = feedBackService.getFeedbacksForNurse();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/forDoctor")
    public ResponseEntity<List<FeedBackForDoctorDTO>> getFeedbacksForDoctor() {
        List<FeedBackForDoctorDTO> feedbacks = feedBackService.getFeedbacksForDoctor();
        return ResponseEntity.ok(feedbacks);
    }

}
