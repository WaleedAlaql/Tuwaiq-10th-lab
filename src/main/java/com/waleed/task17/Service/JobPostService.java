package com.waleed.task17.Service;

import com.waleed.task17.Entity.JobPost;
import com.waleed.task17.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost) {
        jobPost.setPostingDate(LocalDate.now());
        jobPostRepository.save(jobPost);
    }

    public boolean updateJobPost(Long id, JobPost updatedJobPost) {
        JobPost jobPost = jobPostRepository.findById(id).orElse(null);
        if (jobPost == null) {
            return false;
        }
        jobPost.setTitle(updatedJobPost.getTitle());
        jobPost.setDescription(updatedJobPost.getDescription());
        jobPost.setLocation(updatedJobPost.getLocation());
        jobPost.setSalary(updatedJobPost.getSalary());
        jobPostRepository.save(jobPost);
        return true;
    }

    public boolean deleteJobPost(Long id) {
        if (!jobPostRepository.existsById(id)) {
            return false;
        }
        jobPostRepository.deleteById(id);
        return true;
    }
}