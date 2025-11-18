package com.juandavyc.participants.infrastructure.adapter.repository;

import com.juandavyc.participants.infrastructure.adapter.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, UUID> {

    List<ParticipantEntity> findAllByOrderByCreatedAtDesc();
    boolean existsByEmail(String email);
}
