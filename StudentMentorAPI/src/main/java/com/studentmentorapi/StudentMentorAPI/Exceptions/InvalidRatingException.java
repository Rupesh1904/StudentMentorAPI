package com.studentmentorapi.StudentMentorAPI.Exceptions;

public class InvalidRatingException extends RuntimeException {
    public InvalidRatingException(String message) {
        super(message);
    }
}