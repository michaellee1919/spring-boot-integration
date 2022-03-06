package com.zimu.boot.integration.common.redis;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisSingleProperties {

    private String address;

    private int connPoolSize;

    private int connMinIdle;

}
