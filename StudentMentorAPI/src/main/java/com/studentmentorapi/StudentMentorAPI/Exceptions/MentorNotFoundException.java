package com.studentmentorapi.StudentMentorAPI.Exceptions;

public class MentorNotFoundException extends RuntimeException {
    public MentorNotFoundException(String message) {
        super(message);
    }
}