package com.juandavyc.judges.infrastructure.rest.controller;

import com.juandavyc.judges.application.dto.*;
import com.juandavyc.judges.application.usecases.JudgeService;
import com.juandavyc.judges.infrastructure.rest.dto.JudgeRestRequest;
import com.juandavyc.judges.infrastructure.rest.dto.JudgeRestResponse;
import com.juandavyc.judges.infrastructure.rest.mapper.JudgeWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/judges")
@RequiredArgsConstructor
public class JudgeController {

    private final JudgeService service;
    private final JudgeWebMapper webMapper;

    @PostMapping
    public ResponseEntity<JudgeRestResponse> create(
            @RequestBody JudgeRestRequest request
    ) {
        CreateJudgeCommand command = webMapper.toCommand(request);
        JudgeResponse response = service.create(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(webMapper.toResponseDto(response));
    }

    @GetMapping
    public ResponseEntity<List<JudgeRestResponse>> getAll() {
        List<JudgeResponse> response = service.getALl();

        List<JudgeRestResponse> responseDto = response.stream()
                .map(webMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JudgeRestResponse> getById(
            @PathVariable UUID id
    ) {
        JudgeResponse response = service.getById(id);

        JudgeRestResponse responseDto = webMapper.toResponseDto(response);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JudgeRestResponse> update(
            @PathVariable UUID id,
            @RequestBody JudgeRestRequest request
    ) {

        UpdateJudgeCommand command = webMapper.toUpdateCommand(request);

        JudgeResponse response = service.update(id, command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(webMapper.toResponseDto(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable UUID id
    ) {

        service.delete(id);
        return ResponseEntity.noContent().build();

    }


}
