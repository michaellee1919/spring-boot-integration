package com.zimu.boot.integration.common.redis;

import com.google.common.base.Splitter;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Autowired
    RedisProperties redisProperties;

    @Configuration
    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redis.mode}'=='single' or '${spring.redis.mode}'=='cluster' or '${spring.redis.mode}'=='sentinel'")
    protected class RedissonSingleClientConfiguration {

        private String handlePrefix(String node) {
            return node.startsWith("redis://") ? node : "redis://" + node;
        }

        /**
         * 单机模式 redisson 客户端
         */

        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single")
        RedissonClient redissonSingle() {
            Config config = new Config();
            String node = redisProperties.getSingle().getAddress();
            node = node.startsWith("redis://") ? node : "redis://" + node;
            config.useSingleServer()
                    .setAddress(node)
                    .setPassword(redisProperties.getPassword())
                    .setTimeout(redisProperties.getTimeout())
                    .setConnectTimeout(redisProperties.getConnTimeout())
                    .setRetryAttempts(redisProperties.getRetryAttempts())
                    .setRetryInterval(redisProperties.getRetryInterval())
                    .setIdleConnectionTimeout(redisProperties.getPool().getIdleConnTimeout())
                    .setConnectionPoolSize(redisProperties.getSingle().getConnPoolSize())
                    .setConnectionMinimumIdleSize(redisProperties.getSingle().getConnMinIdle());
            return Redisson.create(config);
        }


        /**
         * 集群模式的 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
        RedissonClient redissonCluster() {
            Config config = new Config();
            String[] nodes = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(redisProperties.getCluster().getNodes())
                    .stream().map(this::handlePrefix).toArray(String[]::new);

            config.useClusterServers()
                    .addNodeAddress(nodes)
                    .setPassword(redisProperties.getPassword())
                    .setTimeout(redisProperties.getTimeout())
                    .setConnectTimeout(redisProperties.getConnTimeout())
                    .setRetryAttempts(redisProperties.getRetryAttempts())
                    .setRetryInterval(redisProperties.getRetryInterval())
                    .setIdleConnectionTimeout(redisProperties.getPool().getIdleConnTimeout())
                    .setReadMode(ReadMode.valueOf(redisProperties.getMs().getReadMode()))
                    .setMasterConnectionPoolSize(redisProperties.getMs().getMasterConnPoolSize())
                    .setMasterConnectionMinimumIdleSize(redisProperties.getMs().getMasterConnMinIdleSize())
                    .setSlaveConnectionPoolSize(redisProperties.getMs().getSlaveConnPoolSize())
                    .setSlaveConnectionMinimumIdleSize(redisProperties.getMs().getSlaveConnMinIdleSize())
                    .setScanInterval(redisProperties.getCluster().getScanInterval());
            return Redisson.create(config);
        }

        /**
         * 哨兵模式 redisson 客户端
         * @return
         */

        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
        RedissonClient redissonSentinel() {
            Config config = new Config();
            String[] nodes = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(redisProperties.getSentinel().getNodes())
                    .stream().map(this::handlePrefix).toArray(String[]::new);

            config.useSentinelServers()
                    .addSentinelAddress(nodes)
                    .setPassword(redisProperties.getPassword())
                    .setTimeout(redisProperties.getTimeout())
                    .setConnectTimeout(redisProperties.getConnTimeout())
                    .setRetryAttempts(redisProperties.getRetryAttempts())
                    .setRetryInterval(redisProperties.getRetryInterval())
                    .setIdleConnectionTimeout(redisProperties.getPool().getIdleConnTimeout())
                    .setReadMode(ReadMode.valueOf(redisProperties.getMs().getReadMode()))
                    .setMasterConnectionPoolSize(redisProperties.getMs().getMasterConnPoolSize())
                    .setMasterConnectionMinimumIdleSize(redisProperties.getMs().getMasterConnMinIdleSize())
                    .setSlaveConnectionPoolSize(redisProperties.getMs().getSlaveConnPoolSize())
                    .setSlaveConnectionMinimumIdleSize(redisProperties.getMs().getSlaveConnMinIdleSize())
                    .setMasterName(redisProperties.getSentinel().getMaster())
                    .setScanInterval(redisProperties.getSentinel().getScanInterval());
            return Redisson.create(config);
        }
    }

}
