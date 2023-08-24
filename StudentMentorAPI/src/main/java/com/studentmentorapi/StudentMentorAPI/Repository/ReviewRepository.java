package com.studentmentorapi.StudentMentorAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmentorapi.StudentMentorAPI.Entity.Review;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Add any specific methods if needed
}
