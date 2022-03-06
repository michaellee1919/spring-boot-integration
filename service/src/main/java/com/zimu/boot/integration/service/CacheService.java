package com.zimu.boot.integration.service;

public interface CacheService {

    void put(String key, Object value);

    Object gey(String key);
}
