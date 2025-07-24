package com.example.jrrd.jobapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public java.util.Map<String, String> root() {
        return java.util.Collections.singletonMap("message", "JobApp REST API is running.");
    }

}
