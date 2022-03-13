package com.zimu.boot.integration.common.redis;

import lombok.Data;
import lombok.ToString;
import org.redisson.client.codec.Codec;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
@Data
@ToString
public class RedisProperties {

    private int database;

    private String password;

    private int timeout;

    private int connTimeout;

    private int retryAttempts;

    private int retryInterval;

    private Codec codec;

    /**
     * 池配置
     */
    private RedisPoolProperties pool;

    /**
     * 单机信息配置
     */
    private RedisSingleProperties single;

    /**
     * 主从信息配置
     */
    private RedisMasterSlaveProperties ms;

    /**
     * 集群信息配置
     */
    private RedisClusterProperties cluster;

    /**
     * 哨兵配置
     */
    private RedisSentinelProperties sentinel;

}
