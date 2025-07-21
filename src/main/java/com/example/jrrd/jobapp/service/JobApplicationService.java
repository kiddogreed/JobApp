package com.example.jrrd.jobapp.service;

import com.example.jrrd.jobapp.model.JobApplication;
import com.example.jrrd.jobapp.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobApplicationService {
    @Autowired
    private JobApplicationRepository repository;

    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    public List<JobApplication> getApplicationsByJob(int jobPostId) {
        return repository.findByJobPostId(jobPostId);
    }

    public List<JobApplication> getApplicationsByApplicant(String username) {
        return repository.findByApplicant(username);
    }

    public JobApplication submitApplication(JobApplication application) {
        application.setStatus("PENDING");
        return repository.save(application);
    }

    public void updateStatus(int applicationId, String status) {
        repository.updateStatus(applicationId, status);
    }
}
