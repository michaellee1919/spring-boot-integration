package com.zimu.boot.integration.common.redis;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisClusterProperties {

    /**
     * 集群状态扫描间隔时间，单位是毫秒
     */
    private int scanInterval;

    /**
     * 集群节点
     */
    private String nodes;

}
