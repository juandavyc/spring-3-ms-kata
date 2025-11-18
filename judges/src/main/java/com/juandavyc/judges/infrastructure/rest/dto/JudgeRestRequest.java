package com.juandavyc.judges.infrastructure.rest.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class JudgeRestRequest {

    public interface Create {}
    public interface Update {}

    @NotBlank(message = "Name is required", groups = Create.class)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String specialization;
}
