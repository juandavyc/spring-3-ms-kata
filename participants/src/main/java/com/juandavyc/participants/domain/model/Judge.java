package com.juandavyc.participants.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Judge {

    private UUID id;
    private String name;
    private String email;
    private String specialization;
    private LocalDateTime createdAt;

    public Judge() {
    }

    public Judge(UUID id, String name, String email, String specialization, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.specialization = specialization;
        this.createdAt = createdAt;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Judge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

}
