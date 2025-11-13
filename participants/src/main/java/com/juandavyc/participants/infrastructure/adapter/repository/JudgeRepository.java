package com.juandavyc.participants.infrastructure.adapter.repository;

import com.juandavyc.participants.infrastructure.adapter.entity.JudgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JudgeRepository extends JpaRepository<JudgeEntity, UUID> {

}
