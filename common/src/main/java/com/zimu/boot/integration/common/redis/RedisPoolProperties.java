package com.zimu.boot.integration.common.redis;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisPoolProperties {

    private int idleConnTimeout;

}
