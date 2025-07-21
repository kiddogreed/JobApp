package com.example.jrrd.jobapp.model;

public class JobApplication {
    private int applicationId;
    private int jobPostId;
    private String postProfile; // e.g. job title or profile
    private String applicantUsername;
    private String applicantName;
    private String applicantEmail;
    private String message;
    private String attachment; // file name or path
    private String resume;
    private String status; // PENDING, ACCEPTED, REJECTED

    public JobApplication() {}

    public JobApplication(int applicationId, int jobPostId, String postProfile, String applicantUsername, String applicantName, String applicantEmail, String message, String attachment, String resume, String status) {
        this.applicationId = applicationId;
        this.jobPostId = jobPostId;
        this.postProfile = postProfile;
        this.applicantUsername = applicantUsername;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.message = message;
        this.attachment = attachment;
        this.resume = resume;
        this.status = status;
    }

    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }
    public int getJobPostId() { return jobPostId; }
    public void setJobPostId(int jobPostId) { this.jobPostId = jobPostId; }
    public String getPostProfile() { return postProfile; }
    public void setPostProfile(String postProfile) { this.postProfile = postProfile; }
    public String getApplicantUsername() { return applicantUsername; }
    public void setApplicantUsername(String applicantUsername) { this.applicantUsername = applicantUsername; }
    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
    public String getApplicantEmail() { return applicantEmail; }
    public void setApplicantEmail(String applicantEmail) { this.applicantEmail = applicantEmail; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getAttachment() { return attachment; }
    public void setAttachment(String attachment) { this.attachment = attachment; }
    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
