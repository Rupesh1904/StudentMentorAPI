package com.studentmentorapi.StudentMentorAPI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.studentmentorapi.StudentMentorAPI.Entity.Mentor;
import com.studentmentorapi.StudentMentorAPI.Entity.User1;
import com.studentmentorapi.StudentMentorAPI.Exceptions.EntityNotFoundException;
import com.studentmentorapi.StudentMentorAPI.Exceptions.InvalidRatingException;
import com.studentmentorapi.StudentMentorAPI.Exceptions.MentorNotFoundException;
import com.studentmentorapi.StudentMentorAPI.Repository.MentorRepository;
import com.studentmentorapi.StudentMentorAPI.Repository.RecommendStudentRepository;
import com.studentmentorapi.StudentMentorAPI.Repository.ReviewRepository;
import com.studentmentorapi.StudentMentorAPI.Repository.UserRepository;
import com.studentmentorapi.StudentMentorAPI.Service.MentorService;

@SpringBootTest
public class MentorServiceTests {

    @MockBean
    private MentorRepository mentorRepository;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private RecommendStudentRepository recommendStudentRepository;

    @MockBean
    private UserRepository userRepository;

    private MentorService mentorService;

    private Mentor mentor;
    private User1 user;

    @BeforeEach
    public void setUp() {
        mentorService = new MentorService(mentorRepository, reviewRepository, recommendStudentRepository, userRepository);

        mentor = new Mentor();
        // Set up mentor properties
        
        user = new User1();
        // Set up user properties
    }

    @Test
    public void testRateMentor_ValidRating_Success() {
        when(mentorRepository.findById(any())).thenReturn(Optional.of(mentor));

        mentorService.rateMentor(1L, 4);

        verify(mentorRepository, times(1)).save(any());
    }

    @Test
    public void testRateMentor_InvalidRating_Exception() {
        when(mentorRepository.findById(any())).thenReturn(Optional.of(mentor));

        // Rating below valid range
        try {
            mentorService.rateMentor(1L, 0);
        } catch (InvalidRatingException e) {
            // Expected exception
        }

        // Rating above valid range
        try {
            mentorService.rateMentor(1L, 6);
        } catch (InvalidRatingException e) {
            // Expected exception
        }

        verify(mentorRepository, never()).save(any());
    }

    @Test
    public void testRateMentor_MentorNotFound_Exception() {
        when(mentorRepository.findById(any())).thenReturn(Optional.empty());

        try {
            mentorService.rateMentor(1L, 4);
        } catch (MentorNotFoundException e) {
            // Expected exception
        }

        verify(mentorRepository, never()).save(any());
    }

    @Test
    public void testRecommendStudent_ValidData_Success() {
        when(mentorRepository.findById(any())).thenReturn(Optional.of(mentor));

        mentorService.recommendStudent(1L, "Great student!");

        verify(recommendStudentRepository, times(1)).save(any());
    }

    @Test
    public void testRecommendStudent_MentorNotFound_Exception() {
        when(mentorRepository.findById(any())).thenReturn(Optional.empty());

        try {
            mentorService.recommendStudent(1L, "Great student!");
        } catch (MentorNotFoundException e) {
            // Expected exception
        }

        verify(recommendStudentRepository, never()).save(any());
    }

    @Test
    public void testReviewMentor_ValidData_Success() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(mentorRepository.findById(any())).thenReturn(Optional.of(mentor));

        mentorService.reviewMentor(1L, 1L, "This mentor is amazing!");

        verify(reviewRepository, times(1)).save(any());
    }

    @Test
    public void testReviewMentor_UserOrMentorNotFound_Exception() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(mentorRepository.findById(any())).thenReturn(Optional.empty());

        try {
            mentorService.reviewMentor(1L, 1L, "This mentor is amazing!");
        } catch (EntityNotFoundException e) {
            // Expected exception
        }

        verify(reviewRepository, never()).save(any());
    }

    // Add more test cases as needed

}
