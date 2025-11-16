package com.juandavyc.ranking.application.consumer;

import com.juandavyc.ranking.domain.model.kafka.Evaluation;
import com.juandavyc.ranking.domain.service.EvaluationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationCreatedConsumer {

    private final EvaluationProcessor evaluationProcessor;

    @KafkaListener(
            topics = "evaluation-created-events-topic",
            groupId = "ranking-ms",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeEvaluationCreated(@Payload Evaluation event) {
        try {
            System.out.println("Recibido evento de evaluación: " + event.getId() +
                    " para candidato: " + event.getParticipantId());

            evaluationProcessor.processEvaluation(event);

            System.out.println(" Evento procesado exitosamente: " + event.getId());
        } catch (Exception e) {
            System.err.println(" Error procesando evento: " + event.getId() + " - " + e.getMessage());
            // Podrías implementar dead letter queue o reintentos aquí
            throw e; // Para que Kafka reintente
        }
    }

}
