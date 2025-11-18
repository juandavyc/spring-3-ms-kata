package com.juandavyc.evaluations.infrastructure.feign;

import com.juandavyc.evaluations.infrastructure.feign.dto.JudgeRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "judges")
public interface JudgeFeignClient {

    @GetMapping("/judges/{id}")
    JudgeRestResponse getById(@PathVariable("id") UUID id);

    @GetMapping("/judges/id/{id}/exists")
    boolean existsById(@PathVariable("id") UUID id);

}
