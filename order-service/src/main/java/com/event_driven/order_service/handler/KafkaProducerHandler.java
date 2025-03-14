package com.event_driven.order_service.handler;

import com.event_driven.order_service.service.impl.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.security.KeyException;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaProducerHandler implements KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerHandler.class);
    private static final String KAFKA_PRODUCER = "kafkaProducer";

    @Autowired
    KafkaDlqProducerHandler kafkaDlqProducerHandler;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Retry(name = KAFKA_PRODUCER)
    @CircuitBreaker(name = KAFKA_PRODUCER,fallbackMethod = "handleKafkaFailure")
    public void produce(String topic, String event) throws KeyException {
        try {
            kafkaTemplate.send(topic,event).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new KeyException("failed to send event to kafka",e);
        }
    }

    public void handleKafkaFailure(String topic,String event,Throwable t){
        log.error("kafka event publishing failed. Sending to DLQ ",t);
        kafkaDlqProducerHandler.produce(topic,event);
    }

}
