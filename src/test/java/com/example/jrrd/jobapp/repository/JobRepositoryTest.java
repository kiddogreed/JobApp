package com.example.jrrd.jobapp.repository;

import com.example.jrrd.jobapp.model.JobPost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class JobRepositoryTest {

    @Test
    void testReturnAllJobPosts_NotEmpty() {
        JobRepository repo = new JobRepository();
        List<JobPost> jobs = repo.returnAllJobPosts();
        Assertions.assertFalse(jobs.isEmpty(), "Job list should not be empty");
    }

    @Test
    void testAddJobPost_IncreasesSize() {
        JobRepository repo = new JobRepository();
        int initialSize = repo.returnAllJobPosts().size();
        JobPost newJob = new JobPost(100, "Test Role", "Test Desc", 1, List.of("Test Tech"));
        repo.addJobPost(newJob);
        int newSize = repo.returnAllJobPosts().size();
        Assertions.assertEquals(initialSize + 1, newSize, "Job list size should increase by 1");
        Assertions.assertTrue(repo.returnAllJobPosts().contains(newJob), "New job should be in the list");
    }
}
