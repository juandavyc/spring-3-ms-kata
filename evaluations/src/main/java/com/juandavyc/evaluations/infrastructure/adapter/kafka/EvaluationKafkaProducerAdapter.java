package com.juandavyc.evaluations.infrastructure.adapter.kafka;

import com.juandavyc.evaluations.domain.model.Evaluation;
import com.juandavyc.evaluations.domain.port.event.EvaluationEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationKafkaProducerAdapter implements EvaluationEventPublisherPort {

    private final KafkaTemplate<String, Evaluation> kafkaTemplate;
    private final String topic = "evaluation-created-events-topic";

    @Override
    public void publishEvaluationCreated(Evaluation evaluation) {
        kafkaTemplate.send(topic, evaluation.getId().toString(), evaluation);
    }
}
