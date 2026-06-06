package com.Chan.InventoryService.Kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "inventory-events";

    public void sendInventoryEvent(InventoryEvent event) {
        kafkaTemplate.send(TOPIC, event.getId(), event);
    }
}
