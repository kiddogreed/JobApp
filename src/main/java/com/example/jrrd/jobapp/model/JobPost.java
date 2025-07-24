package com.example.jrrd.jobapp.model;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class JobPost {
@Id
@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
@Column(name = "id")
private int id;

@Column(name = "title")
private String title;

@Column(name = "description")
private String description;

@Column(name = "experience")
private Integer experience;

@Column(name = "skills")
private String skills; // Comma-separated string
}
