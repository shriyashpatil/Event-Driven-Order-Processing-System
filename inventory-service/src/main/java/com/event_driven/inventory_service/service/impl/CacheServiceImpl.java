package com.event_driven.inventory_service.service.impl;

import com.event_driven.inventory_service.handler.KafkaOrderEventConsumerHandler;
import com.event_driven.inventory_service.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {

    private static final Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);
    private final RedisTemplate<String,String> redisTemplate;

    public CacheServiceImpl(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setData(String key,String value,long timeout){
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.MINUTES);
        log.info("Added key : {} and value: {}",key,value);
    }

    public String getData(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key){
        redisTemplate.delete(key);
    }


}
