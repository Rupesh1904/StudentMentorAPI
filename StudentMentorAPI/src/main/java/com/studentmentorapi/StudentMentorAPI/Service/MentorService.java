package com.studentmentorapi.StudentMentorAPI.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmentorapi.StudentMentorAPI.Entity.Mentor;
import com.studentmentorapi.StudentMentorAPI.Entity.RecommendStudent;
import com.studentmentorapi.StudentMentorAPI.Entity.Review;
import com.studentmentorapi.StudentMentorAPI.Entity.User1;
import com.studentmentorapi.StudentMentorAPI.Exceptions.EntityNotFoundException;
import com.studentmentorapi.StudentMentorAPI.Exceptions.InvalidRatingException;
import com.studentmentorapi.StudentMentorAPI.Exceptions.MentorNotFoundException;
import com.studentmentorapi.StudentMentorAPI.Repository.MentorRepository;
import com.studentmentorapi.StudentMentorAPI.Repository.RecommendStudentRepository;
import com.studentmentorapi.StudentMentorAPI.Repository.ReviewRepository;
import com.studentmentorapi.StudentMentorAPI.Repository.UserRepository;

@Service
public class MentorService {
     @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RecommendStudentRepository recommendStudentRepository;

    @Autowired
    private UserRepository userRepository;

    public void rateMentor(Long mentorId, int rating) {
        Optional<Mentor> mentorOptional = mentorRepository.findById(mentorId);
            if (mentorOptional.isPresent()) {
                Mentor mentor = mentorOptional.get();
                if (rating >= 1 && rating <= 5) {
                    double newRating = (mentor.getRating() + rating) / 2.0; // Calculate new average rating
                    mentor.setRating(newRating);
                    mentorRepository.save(mentor);
                } else {
                    throw new InvalidRatingException("Invalid rating. Rating must be between 1 and 5.");
                }
            } else {
                throw new MentorNotFoundException("Mentor not found with id: " + mentorId);
            }
    }

    public void recommendStudent(Long mentorId, String recommendation) {
        Optional<Mentor> mentorOptional = mentorRepository.findById(mentorId);

        if (mentorOptional.isPresent()) {
            Mentor mentor = mentorOptional.get();

            RecommendStudent recommendStudent = new RecommendStudent();
            recommendStudent.setMentor(mentor);
            recommendStudent.setRecommendation(recommendation);

            recommendStudentRepository.save(recommendStudent);
        } else {
            throw new MentorNotFoundException("Mentor not found with id: " + mentorId);
        }
    }

    public void reviewMentor(Long userId, Long mentorId, String reviewText) {
    Optional<User1> userOptional = userRepository.findById(userId);
    Optional<Mentor> mentorOptional = mentorRepository.findById(mentorId);
    
    if (userOptional.isPresent() && mentorOptional.isPresent()) {
        User1 user = userOptional.get();
        Mentor mentor = mentorOptional.get();
        
        Review review = new Review();
        review.setUser(user);
        review.setMentor(mentor);
        review.setReviewText(reviewText);
        
        reviewRepository.save(review);
    } else {
        throw new EntityNotFoundException("User or Mentor not found");
    }
}

public List<Mentor> getMentorsByRating(Integer rating) {
    if (rating != null) {
        return mentorRepository.findByRating(rating);
    } else {
        return mentorRepository.findAll();
    }
}

public String getRecommendationByMentorId(Long mentorId) {
    Optional<Mentor> mentorOptional = mentorRepository.findById(mentorId);
    if (mentorOptional.isPresent()) {
        Mentor mentor = mentorOptional.get();
        Optional<RecommendStudent> recommendOptional = recommendStudentRepository.findByMentor(mentor);
        if (recommendOptional.isPresent()) {
            return recommendOptional.get().getRecommendation();
        } else {
            return "No recommendation available for this mentor.";
        }
    } else {
        throw new MentorNotFoundException("Mentor not found with id: " + mentorId);
    }
    }
    public Mentor createMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }
    public User1 createUser(User1 user) {
        return userRepository.save(user);
    }
    public Mentor updateMentorRating(Long mentorId, double newRating) {
        Optional<Mentor> mentorOptional = mentorRepository.findById(mentorId);
        if (mentorOptional.isPresent()) {
            Mentor mentor = mentorOptional.get();
            mentor.setRating(newRating);
            return mentorRepository.save(mentor);
        } else {
            throw new MentorNotFoundException("Mentor not found with id: " + mentorId);
        }
    }
   



}
