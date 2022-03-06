package com.zimu.boot.integration.common.redis;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisSentinelProperties {

    /**
     * 哨兵状态扫描间隔时间，单位是毫秒
     */
    private int scanInterval;

    /**
     * 哨兵master 名称
     */
    private String master;

    /**
     * 哨兵节点
     */
    private String nodes;

}
