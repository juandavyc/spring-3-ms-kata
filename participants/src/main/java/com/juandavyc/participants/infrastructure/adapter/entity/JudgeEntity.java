package com.juandavyc.participants.infrastructure.adapter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "judges", schema = "participants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JudgeEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String email;
    private String specialization;
    private LocalDateTime createdAt;
    //private LocalDateTime updatedAt;

}