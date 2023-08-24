package com.studentmentorapi.StudentMentorAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmentorapi.StudentMentorAPI.Entity.Mentor;
import com.studentmentorapi.StudentMentorAPI.Entity.RecommendStudent;


public interface RecommendStudentRepository extends JpaRepository<RecommendStudent, Long> {

    Optional<RecommendStudent> findByMentor(Mentor mentor);
    // Add any specific methods if needed
}
