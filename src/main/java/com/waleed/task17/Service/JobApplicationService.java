package com.waleed.task17.Service;

import com.waleed.task17.Entity.JobApplication;
import com.waleed.task17.Repository.JobApplicationRepository;
import com.waleed.task17.Repository.JobPostRepository;
import com.waleed.task17.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public int applyForJob(JobApplication jobApplication) {
        if (!userRepository.existsById(jobApplication.getUserId())) {
            return 1;
        }
        if (!jobPostRepository.existsById(jobApplication.getJobPostId())) {
            return 2;
        }
        jobApplicationRepository.save(jobApplication);
        return 0;
    }

    public boolean withdrawJobApplication(Long id) {
        if (!jobApplicationRepository.existsById(id)) {
            return false;
        }
        jobApplicationRepository.deleteById(id);
        return true;
    }
}