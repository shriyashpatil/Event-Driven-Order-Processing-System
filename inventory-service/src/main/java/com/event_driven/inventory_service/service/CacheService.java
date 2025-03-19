package com.event_driven.inventory_service.service;

public interface CacheService {
    public void setData(String key,String value,long timeout);
    public String getData(String key);

    public void deleteData(String key);
}
