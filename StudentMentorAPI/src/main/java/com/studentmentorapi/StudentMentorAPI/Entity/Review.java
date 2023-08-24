package com.studentmentorapi.StudentMentorAPI.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User1 user;

    @ManyToOne
    private Mentor mentor;

    private int rating;
    private String reviewText;



    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User1 getUser() {
        return user;
    }
    public void setUser(User1 user) {
        this.user = user;
    }
    public Mentor getMentor() {
        return mentor;
    }
    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }
    public String getReviewText() {
        return reviewText;
    }
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

}