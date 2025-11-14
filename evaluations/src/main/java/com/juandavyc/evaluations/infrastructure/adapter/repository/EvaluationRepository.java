package com.juandavyc.evaluations.infrastructure.adapter.repository;

import com.juandavyc.evaluations.infrastructure.adapter.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationEntity, UUID> {

}
