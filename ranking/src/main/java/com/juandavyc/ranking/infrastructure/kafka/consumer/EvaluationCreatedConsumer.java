package com.juandavyc.ranking.infrastructure.kafka.consumer;

import com.juandavyc.ranking.application.dto.EvaluationCommand;
import com.juandavyc.ranking.application.usecases.EvaluationUseCase;
import com.juandavyc.ranking.infrastructure.kafka.dto.EvaluationEvent;
import com.juandavyc.ranking.infrastructure.kafka.mapper.EvaluationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EvaluationCreatedConsumer {

    private final EvaluationUseCase evaluationUseCase;
    private final EvaluationMapper mapper; //

    @KafkaListener(
            topics = "evaluation-created-events-topic",
            groupId = "ranking-ms",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeEvaluationCreated(@Payload EvaluationEvent event) {
        try {
            System.out.println("Recibido evento de evaluación: " + event.getId() +
                    " para candidato: " + event.getParticipantId());

            // Convertir el DTO de Kafka a un objeto del dominio/application
            EvaluationCommand evaluationCommand = mapper.toCommand(event);

            // Llamar al caso de uso (que ahora está en application)
            evaluationUseCase.processEvaluation(evaluationCommand);

            System.out.println("Evento procesado exitosamente: " + event.getId());
        } catch (Exception e) {
            System.err.println("Error procesando evento: " + event.getId() + " - " + e.getMessage());
            throw e; // Para reintentos de Kafka
        }
    }
}
