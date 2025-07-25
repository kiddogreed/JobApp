package com.example.jrrd.jobapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jrrd.jobapp.model.JobPost;
import com.example.jrrd.jobapp.repository.JobRepository;

@Service
public class JobService {
  // Injecting JobRepository to access its methods  
  @Autowired
  private JobRepository repo;
  //method to return all JobPosts
	public List<JobPost> returnAllJobPosts() {
		return repo.returnAllJobPosts();

		
	}
	
	
	
	

	// ***************************************************************************


	
	
	
	// method to add a jobPost
	public void addJobPost(JobPost jobPost) {
		 repo.addJobPost(jobPost);
	
	}
  
}
