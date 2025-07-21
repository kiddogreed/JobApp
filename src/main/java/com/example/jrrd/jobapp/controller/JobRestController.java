package com.example.jrrd.jobapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jrrd.jobapp.model.JobPost;
import com.example.jrrd.jobapp.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin // Allow all origins for REST API
@RestController
@RequestMapping("/api/jobs")
public class JobRestController {

    private final JobService service;

    public JobRestController(JobService service) {
        this.service = service;
    }

    @Operation(summary = "Get all job posts", description = "Returns a list of all job posts as JSON.")
    @GetMapping
    public List<JobPost> getAllJobs() {
        return service.returnAllJobPosts();
    }

    @Operation(summary = "Add a new job post", description = "Adds a new job post and returns the added job post as JSON.")
    @PostMapping
    public JobPost addJob(@Parameter(description = "Job post to add") @RequestBody JobPost jobPost) {
        service.addJobPost(jobPost);
        return jobPost;
    }

    @Operation(summary = "Get a job post by ID", description = "Returns a job post by its ID.")
    @GetMapping("/{id}")
    public JobPost getJobById(@PathVariable int id) {
        return service.returnAllJobPosts().stream()
                .filter(job -> job.getPostId() == id)
                .findFirst()
                .orElse(null);
    }

    @Operation(summary = "Update a job post", description = "Updates a job post by its ID and returns the updated job post.")
    @PutMapping("/{id}")
    public JobPost updateJob(@PathVariable int id, @RequestBody JobPost updatedJob) {
        List<JobPost> jobs = service.returnAllJobPosts();
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getPostId() == id) {
                jobs.set(i, updatedJob);
                return updatedJob;
            }
        }
        return null;
    }

    @Operation(summary = "Delete a job post", description = "Deletes a job post by its ID.")
    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable int id) {
        List<JobPost> jobs = service.returnAllJobPosts();
        jobs.removeIf(job -> job.getPostId() == id);
    }
}
