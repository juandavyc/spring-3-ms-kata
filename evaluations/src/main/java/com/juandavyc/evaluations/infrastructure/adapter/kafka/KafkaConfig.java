package com.juandavyc.evaluations.infrastructure.adapter.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

//@Configuration
public class KafkaConfig {

//    @Bean
//    NewTopic evaluationTopic() {
//        return TopicBuilder
//                .name("evaluation-created-events-topic")
//                .partitions(3) // 3 ms consume of this
//                .replicas(1)// copy of different brokers
//                .configs(Map.of("min.insync.replicas", "1"))
//                .build();
//    }

}
