package com.studentmentorapi.StudentMentorAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmentorapi.StudentMentorAPI.Entity.User1;



public interface UserRepository extends JpaRepository<User1, Long> {
    // Add any specific methods if needed
}