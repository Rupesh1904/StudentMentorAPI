package com.studentmentorapi.StudentMentorAPI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentmentorapi.StudentMentorAPI.Controller.MentorController;
import com.studentmentorapi.StudentMentorAPI.Entity.Mentor;
import com.studentmentorapi.StudentMentorAPI.Entity.User1;
import com.studentmentorapi.StudentMentorAPI.Service.MentorService;

@WebMvcTest(MentorController.class)
public class MentorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MentorService mentorService;

    private Mentor mentor;
    private User1 user;

    @BeforeEach
    public void setUp() {
        mentor = new Mentor();
        // Set up mentor properties
        
        user = new User1();
        // Set up user properties
    }

    @Test
    public void testGetMentors() throws Exception {
        List<Mentor> mentors = Arrays.asList(mentor);

        when(mentorService.getMentorsByRating(any())).thenReturn(mentors);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/mentors"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(mentors.size()));
    }

    @Test
    public void testCreateMentor() throws Exception {
        when(mentorService.createMentor(any())).thenReturn(mentor);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/create-mentor")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(mentor)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void testRecommendStudent() throws Exception {
        Long mentorId = 1L;
        String recommendation = "Great student!";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/recommend-student")
            .param("mentorId", mentorId.toString())
            .param("recommendation", recommendation))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Student recommended successfully"));
    }

    @Test
    public void testRateMentor() throws Exception {
        Long mentorId = 1L;
        int rating = 4;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/rate-mentor")
            .param("mentorId", mentorId.toString())
            .param("rating", String.valueOf(rating)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Mentor rated successfully"));
    }

    @Test
    public void testReviewMentor() throws Exception {
        Long userId = 1L;
        Long mentorId = 1L;
        String reviewText = "This mentor is awesome!";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/review-mentor")
            .param("userId", userId.toString())
            .param("mentorId", mentorId.toString())
            .param("reviewText", reviewText))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Review added successfully"));
    }

    @Test
    public void testGetRecommendation() throws Exception {
        Long mentorId = 1L;
        String recommendation = "Highly recommended!";

        when(mentorService.getRecommendationByMentorId(any())).thenReturn(recommendation);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/recommendation/{mentorId}", mentorId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(recommendation));
    }

    @Test
    public void testUpdateMentorRating() throws Exception {
        Long mentorId = 1L;
        double newRating = 4.5;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/update-mentor-rating")
            .param("mentorId", mentorId.toString())
            .param("newRating", String.valueOf(newRating)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Mentor rating updated successfully"));
    }

    @Test
    public void testCreateUser() throws Exception {
        when(mentorService.createUser(any())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/create-user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(user)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    // Helper method to convert objects to JSON format
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
