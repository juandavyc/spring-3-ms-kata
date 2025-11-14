package com.juandavyc.judges.infrastructure.adapter.repository;

import com.juandavyc.judges.infrastructure.adapter.entity.JudgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JudgeRepository extends JpaRepository<JudgeEntity, UUID> {

}
