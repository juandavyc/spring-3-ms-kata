package com.juandavyc.participants.infrastructure.rest.controller;

import com.juandavyc.participants.application.dto.CreateParticipantCommand;
import com.juandavyc.participants.application.dto.ParticipantResponse;
import com.juandavyc.participants.application.dto.UpdateParticipantCommand;
import com.juandavyc.participants.application.usecases.ParticipantService;
import com.juandavyc.participants.infrastructure.rest.dto.ParticipantRestRequest;
import com.juandavyc.participants.infrastructure.rest.dto.ParticipantRestResponse;
import com.juandavyc.participants.infrastructure.rest.mapper.ParticipantWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService service;
    private final ParticipantWebMapper webMapper;

    @PostMapping
    public ResponseEntity<ParticipantRestResponse> create(
            @RequestBody ParticipantRestRequest request
    ) {

        CreateParticipantCommand command = webMapper.toCommand(request);
        ParticipantResponse response = service.create(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(webMapper.toResponseDto(response));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantRestResponse>> getAll() {
        List<ParticipantResponse> response = service.getAll();

        List<ParticipantRestResponse> responseDto = response.stream()
                .map(webMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantRestResponse> getById(
            @PathVariable UUID id
    ) {
        ParticipantResponse response = service.getById(id);

        ParticipantRestResponse responseDto = webMapper.toResponseDto(response);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> exists(@PathVariable UUID id) {
        boolean exists =  service.existsById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(exists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantRestResponse> update(
            @PathVariable UUID id,
            @RequestBody ParticipantRestRequest request
    ) {

        UpdateParticipantCommand command = webMapper.toUpdateCommand(request);

        ParticipantResponse response = service.update(id, command);

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
