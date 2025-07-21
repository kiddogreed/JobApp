package com.example.jrrd.jobapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.jrrd.jobapp.model.JobPost;
import com.example.jrrd.jobapp.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;




@Controller
public class JobController {

@Autowired
private JobService service;

  @Operation(summary = "Show home page")
  @RequestMapping({"/", "home"})
  public String home() {
    return "home";
  }

  @Operation(summary = "Show add job form")
  @RequestMapping("addjob")
  public String addjob() {
    return "addjob";
  }

  // controller method for getting all job posts
  @Operation(summary = "View all job posts", description = "Returns a list of all job posts.")
	@GetMapping("/viewalljobs")
	public String viewJobs(Model model) {

		List<JobPost> jobPosts = service.returnAllJobPosts();
		model.addAttribute("jobPosts", jobPosts);
		return "viewalljobs";
	}

  @Operation(summary = "Handle add job form submission", description = "Adds a new job post and shows success page.")
  @PostMapping("/handleForm")
	public String handleAddJobForm(@Parameter(description = "Job post to add") JobPost jobPost, Model model) {
		model.addAttribute("jobPost", jobPost);
		service.addJobPost(jobPost);
		//System.out.println(jobPost);
		  return "success";
		
	}
	
  
  
}
