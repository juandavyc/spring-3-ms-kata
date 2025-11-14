package com.juandavyc.judges.application.exceptions;

public class JudgeNotFoundException extends RuntimeException {
    public JudgeNotFoundException(String message) {
        super(message);
    }
}
