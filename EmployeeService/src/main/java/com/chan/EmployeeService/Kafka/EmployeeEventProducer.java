package com.chan.EmployeeService.Kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "employee-events";

    public void sendEmployeeEvent(EmployeeEvent event) {
        kafkaTemplate.send(TOPIC, event.getId(), event);
    }
}
