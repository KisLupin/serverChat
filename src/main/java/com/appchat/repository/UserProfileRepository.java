package com.appchat.repository;

import com.appchat.model.data.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    UserProfile findByUsername(String username);
    UserProfile findById(int id);
}
