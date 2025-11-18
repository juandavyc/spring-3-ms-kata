package com.juandavyc.judges.application.usecases;

import com.juandavyc.judges.application.dto.*;

import java.util.List;
import java.util.UUID;

public interface JudgeService {

    JudgeResponse create(CreateJudgeCommand command);
    List<JudgeResponse> getALl();
    JudgeResponse getById(UUID id);

    boolean existsById(UUID id);
    boolean existsByEmail(String email);

    JudgeResponse update(UUID id, UpdateJudgeCommand command);
    void delete(UUID id);


}