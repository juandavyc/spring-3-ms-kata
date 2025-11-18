package com.juandavyc.participants.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Participant {

    private UUID id;
    private String name;
    private String email;
    private String jobRole;
    private LocalDateTime createdAt;

    public Participant() {
    }

    public Participant(UUID id, String name, String email, String jobRole, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobRole = jobRole;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
