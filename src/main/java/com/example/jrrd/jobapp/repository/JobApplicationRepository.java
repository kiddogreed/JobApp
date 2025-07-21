package com.example.jrrd.jobapp.repository;

import com.example.jrrd.jobapp.model.JobApplication;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class JobApplicationRepository {
    private final List<JobApplication> applications = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<JobApplication> findAll() {
        return new ArrayList<>(applications);
    }

    public List<JobApplication> findByJobPostId(int jobPostId) {
        return applications.stream()
                .filter(app -> app.getJobPostId() == jobPostId)
                .collect(Collectors.toList());
    }

    public List<JobApplication> findByApplicant(String username) {
        return applications.stream()
                .filter(app -> app.getApplicantUsername().equals(username))
                .collect(Collectors.toList());
    }

    public JobApplication save(JobApplication application) {
        application.setApplicationId(idCounter.getAndIncrement());
        applications.add(application);
        return application;
    }

    public JobApplication findById(int id) {
        return applications.stream()
                .filter(app -> app.getApplicationId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateStatus(int id, String status) {
        JobApplication app = findById(id);
        if (app != null) {
            app.setStatus(status);
        }
    }
}
