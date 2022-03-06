package com.zimu.boot.integration.service.impl;

import com.zimu.boot.integration.service.CacheService;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheServiceImpl implements CacheService {

    private RedissonClient redisson;

    public CacheServiceImpl(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public void put(String key, Object value) {
        Map<String, Object> buckets = new HashMap<>(1);
        buckets.put(key, value);
        redisson.getBuckets().set(buckets);
    }

    @Override
    public Object gey(String key) {
        return redisson.getBuckets().get(key);
    }
}
