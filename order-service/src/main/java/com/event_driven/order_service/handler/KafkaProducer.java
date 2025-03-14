package com.event_driven.order_service.handler;

import java.security.KeyException;

public interface KafkaProducer {

    public void produce(String topic, String event) throws KeyException;

}
