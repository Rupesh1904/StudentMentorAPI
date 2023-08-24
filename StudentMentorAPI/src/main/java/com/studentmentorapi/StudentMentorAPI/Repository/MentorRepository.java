package com.studentmentorapi.StudentMentorAPI.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmentorapi.StudentMentorAPI.Entity.Mentor;


public interface MentorRepository extends JpaRepository<Mentor, Long> {

    List<Mentor> findByRating(Integer rating);
    // Add any specific methods if needed
}