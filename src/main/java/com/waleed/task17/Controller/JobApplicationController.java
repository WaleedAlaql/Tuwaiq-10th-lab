package com.waleed.task17.Controller;

import com.waleed.task17.Api.ApiResponse;
import com.waleed.task17.Entity.JobApplication;
import com.waleed.task17.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getAllJobApplications() {
        return ResponseEntity.status(200).body(new ApiResponse("Success", jobApplicationService.getAllJobApplications()));
    }

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse> applyForJob(@Valid @RequestBody JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage(), null));
        }
        int result = jobApplicationService.applyForJob(jobApplication);
        if (result == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("User ID not found", null));
        }
        if (result == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Job Post ID not found", null));
        }
        return ResponseEntity.status(201).body(new ApiResponse("Applied for job successfully", jobApplication));
    }

    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity<ApiResponse> withdrawJobApplication(@PathVariable Long id) {
        boolean deleted = jobApplicationService.withdrawJobApplication(id);
        if (!deleted) {
            return ResponseEntity.status(404).body(new ApiResponse("Job application not found", null));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Job application withdrawn successfully", null));
    }
}