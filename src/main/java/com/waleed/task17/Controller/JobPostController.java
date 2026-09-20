package com.waleed.task17.Controller;

import com.waleed.task17.Api.ApiResponse;
import com.waleed.task17.Entity.JobPost;
import com.waleed.task17.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getAllJobPosts() {
        return ResponseEntity.status(200).body(new ApiResponse("Success", jobPostService.getAllJobPosts()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addJobPost(@Valid @RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage(), null));
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(201).body(new ApiResponse("Job post added successfully", jobPost));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateJobPost(@PathVariable Long id, @Valid @RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage(), null));
        }
        boolean updated = jobPostService.updateJobPost(id, jobPost);
        if (!updated) {
            return ResponseEntity.status(404).body(new ApiResponse("Job post not found", null));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Job post updated successfully", jobPost));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteJobPost(@PathVariable Long id) {
        boolean deleted = jobPostService.deleteJobPost(id);
        if (!deleted) {
            return ResponseEntity.status(404).body(new ApiResponse("Job post not found", null));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Job post deleted successfully", null));
    }
}