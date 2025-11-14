package com.juandavyc.evaluations.infrastructure.rest.controller;

import com.juandavyc.evaluations.application.dto.CreateEvaluationCommand;
import com.juandavyc.evaluations.application.dto.EvaluationResponse;
import com.juandavyc.evaluations.application.dto.UpdateEvaluationCommand;
import com.juandavyc.evaluations.application.usecases.EvaluationService;
import com.juandavyc.evaluations.infrastructure.rest.dto.EvaluationRestRequest;
import com.juandavyc.evaluations.infrastructure.rest.dto.EvaluationRestResponse;
import com.juandavyc.evaluations.infrastructure.rest.mapper.EvaluationWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;
    private final EvaluationWebMapper webMapper;

    @PostMapping
    public ResponseEntity<EvaluationRestResponse> createEvaluation(@RequestBody EvaluationRestRequest request) {
        CreateEvaluationCommand command = webMapper.toCreateCommand(request);
        EvaluationResponse response = evaluationService.createEvaluation(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(webMapper.toRestResponse(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationRestResponse> getEvaluationById(@PathVariable UUID id) {
        EvaluationResponse response = evaluationService.getEvaluationById(id);
        return ResponseEntity.ok(webMapper.toRestResponse(response));
    }

    @GetMapping
    public ResponseEntity<List<EvaluationRestResponse>> getAllEvaluations() {
        List<EvaluationResponse> responses = evaluationService.getAllEvaluations();
        List<EvaluationRestResponse> restResponses = responses.stream()
                .map(webMapper::toRestResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(restResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationRestResponse> updateEvaluation(
            @PathVariable UUID id,
            @RequestBody EvaluationRestRequest request) {
        UpdateEvaluationCommand command = webMapper.toUpdateCommand(request);
        EvaluationResponse response = evaluationService.updateEvaluation(id, command);
        return ResponseEntity.ok(webMapper.toRestResponse(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable UUID id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }

}
