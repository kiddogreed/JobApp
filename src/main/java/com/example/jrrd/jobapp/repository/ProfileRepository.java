package com.example.jrrd.jobapp.repository;

import com.example.jrrd.jobapp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByUserId(int userId);
    Optional<Profile> findByUserName(String userName);
    Optional<Profile> findByEmail(String email);
}
