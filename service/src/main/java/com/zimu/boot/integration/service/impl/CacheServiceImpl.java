package com.zimu.boot.integration.service.impl;

import com.zimu.boot.integration.service.CacheService;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

    private RedissonClient redisson;

    public CacheServiceImpl(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public void put(String key, Object value) {
        redisson.getBucket(key).set(value);
    }

    @Override
    public Object gey(String key) {
        return redisson.getBucket(key).get();
    }
}
