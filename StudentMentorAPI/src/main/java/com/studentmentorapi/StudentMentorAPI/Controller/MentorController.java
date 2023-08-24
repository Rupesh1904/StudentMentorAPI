package com.studentmentorapi.StudentMentorAPI.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.studentmentorapi.StudentMentorAPI.Entity.Mentor;
import com.studentmentorapi.StudentMentorAPI.Entity.User1;
import com.studentmentorapi.StudentMentorAPI.Service.MentorService;

@RestController
@RequestMapping("/api")
public class MentorController {

    @Autowired
    private MentorService mentorService;


    @PostMapping("/recommend-student")
    public ResponseEntity<String> recommendStudent(@RequestParam Long mentorId, @RequestParam String recommendation) {
        mentorService.recommendStudent(mentorId, recommendation);
        return ResponseEntity.ok("Student recommended successfully");
    }

    @PostMapping("/rate-mentor")
    public ResponseEntity<String> rateMentor(@RequestParam Long mentorId, @RequestParam int rating) {
        mentorService.rateMentor(mentorId, rating);
        return ResponseEntity.ok("Mentor rated successfully");
    }

    @PostMapping("/review-mentor")
    public ResponseEntity<String> reviewMentor(@RequestParam Long userId, @RequestParam Long mentorId, @RequestParam String reviewText) {
        mentorService.reviewMentor(userId, mentorId, reviewText);
        return ResponseEntity.ok("Review added successfully");
    }

    @GetMapping("/mentors")
    public ResponseEntity<List<Mentor>> getMentors(@RequestParam(required = false) Integer rating) {
        List<Mentor> mentors = mentorService.getMentorsByRating(rating);
        return ResponseEntity.ok(mentors);
    }

    @GetMapping("/recommendation/{mentorId}")
    public ResponseEntity<String> getRecommendation(@PathVariable Long mentorId) {
    String recommendation = mentorService.getRecommendationByMentorId(mentorId);
    return ResponseEntity.ok(recommendation);
    }
    @PostMapping("/create-mentor")
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor) {
        Mentor createdMentor = mentorService.createMentor(mentor);
        return ResponseEntity.ok(createdMentor);
    }

    @PostMapping("/update-mentor-rating")
    public ResponseEntity<String> updateMentorRating(@RequestParam Long mentorId, @RequestParam double newRating) {
        mentorService.updateMentorRating(mentorId, newRating);
        return ResponseEntity.ok("Mentor rating updated successfully");
    }

    @PostMapping("/create-user")
    public ResponseEntity<User1> createUser(@RequestBody User1 user) {
        User1 createdUser = mentorService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
}

