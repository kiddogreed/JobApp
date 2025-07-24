
package com.example.jrrd.jobapp.controller;

import com.example.jrrd.jobapp.model.Profile;
import com.example.jrrd.jobapp.repository.ProfileRepository;
import com.example.jrrd.jobapp.model.User;
import com.example.jrrd.jobapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileRestController {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileRestController(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }


    // Create profile: matches userId, username, email, role from users
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile, Authentication auth) {
        if (auth == null || auth.getName() == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        User user = userRepository.findByUsername(auth.getName()).orElse(null);
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        profile.setUserId(user.getId());
        profile.setUserName(user.getUsername());
        profile.setEmail(user.getEmail());
        profile.setRole(user.getRole());
        Profile saved = profileRepository.save(profile);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    // Get all profiles (admin only)
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles(Authentication auth) {
        if (auth == null || auth.getName() == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(profileRepository.findAll());
    }

    // Get profile by username: allow user or admin
    @GetMapping("/{username}")
    public ResponseEntity<Profile> getProfileByUsername(@PathVariable String username, Authentication auth) {
        if (auth == null || auth.getName() == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        // Only allow user to access their own profile or admin
        if (!auth.getName().equals(username) && auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        Profile profile = profileRepository.findByUserId(user.getId()).orElse(null);
        if (profile == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(profile);
    }

    // Update profile by username (authenticated user only)
    @PutMapping("/{username}/me")
    public ResponseEntity<Profile> updateProfileByUsername(@PathVariable String username, @RequestBody Profile updates, Authentication auth) {
        if (auth == null || auth.getName() == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        // Only allow user to update their own profile or admin
        if (!auth.getName().equals(username) && auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        Profile profile = profileRepository.findByUserId(user.getId()).orElse(null);
        if (profile == null) return ResponseEntity.notFound().build();
        // Only allow editing names, phone, address, companyName, middleName
        profile.setFirstName(updates.getFirstName());
        profile.setLastName(updates.getLastName());
        profile.setMiddleName(updates.getMiddleName());
        profile.setPhone(updates.getPhone());
        profile.setAddress(updates.getAddress());
        profile.setCompanyName(updates.getCompanyName());
        // Only admin can edit role
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")) && updates.getRole() != null) {
            profile.setRole(updates.getRole());
        }
        Profile saved = profileRepository.save(profile);
        return ResponseEntity.ok(saved);
    }
}
