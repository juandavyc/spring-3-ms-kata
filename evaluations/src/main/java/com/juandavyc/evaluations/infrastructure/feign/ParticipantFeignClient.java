package com.juandavyc.evaluations.infrastructure.feign;


import com.juandavyc.evaluations.infrastructure.feign.dto.ParticipantRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "participants")
public interface ParticipantFeignClient {

    @GetMapping("/participants/{id}")
    ParticipantRestResponse getById(@PathVariable("id") UUID id);

    @GetMapping("/participants/{id}/exists")
    boolean existsById(@PathVariable("id") UUID id);

}
