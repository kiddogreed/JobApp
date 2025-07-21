package com.example.jrrd.jobapp.controller;

import com.example.jrrd.jobapp.model.JobApplication;
import com.example.jrrd.jobapp.service.JobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@Tag(name = "Job Applications", description = "Endpoints for job application submission and management")
public class JobApplicationRestController {
    private final JobApplicationService service;

    public JobApplicationRestController(JobApplicationService service) {
        this.service = service;
    }

    // Jobseeker submits application
    @Operation(summary = "Submit a job application", description = "Allows a jobseeker to submit a new job application.")
    @ApiResponse(responseCode = "200", description = "Application submitted successfully")
    @ApiResponse(responseCode = "403", description = "Forbidden - not a jobseeker")
    @PostMapping
    public ResponseEntity<JobApplication> submitApplication(@RequestBody JobApplication application, Authentication auth) {
        application.setApplicantUsername(auth.getName());
        JobApplication saved = service.submitApplication(application);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Employer reviews all applications for a job
    @Operation(summary = "Get applications for a job", description = "Allows employer or admin to view all applications for a specific job post.")
    @ApiResponse(responseCode = "200", description = "List of applications returned")
    @ApiResponse(responseCode = "403", description = "Forbidden - not employer or admin")
    @GetMapping("/job/{jobPostId}")
    public List<JobApplication> getApplicationsByJob(@PathVariable int jobPostId) {
        return service.getApplicationsByJob(jobPostId);
    }

    // Employer accepts or rejects an application
    @Operation(summary = "Update application status", description = "Allows employer or admin to accept or reject a job application.")
    @ApiResponse(responseCode = "200", description = "Status updated successfully")
    @ApiResponse(responseCode = "403", description = "Forbidden - not employer or admin")
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable int applicationId, @RequestParam String status) {
        service.updateStatus(applicationId, status);
        return ResponseEntity.ok().build();
    }

    // Jobseeker views their own applications
    @Operation(summary = "Get my applications", description = "Allows a jobseeker to view their submitted job applications.")
    @ApiResponse(responseCode = "200", description = "List of applications returned")
    @ApiResponse(responseCode = "403", description = "Forbidden - not a jobseeker")
    @GetMapping("/my")
    public List<JobApplication> getMyApplications(Authentication auth) {
        return service.getApplicationsByApplicant(auth.getName());
    }
}
