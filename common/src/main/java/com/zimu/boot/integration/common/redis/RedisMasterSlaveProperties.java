package com.zimu.boot.integration.common.redis;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisMasterSlaveProperties {

    /**
     * 默认值： SLAVE（只在从服务节点里读取）设置读取操作选择节点的模式。 可用值为： SLAVE - 只在从服务节点里读取。
     * MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取
     */
    private String readMode;

    /**
     * （从节点连接池大小） 默认值：64
     */
    private int slaveConnPoolSize;

    /**
     * 从节点每个从节点的最小空闲连接大小 默认值：24
     */
    private int slaveConnMinIdleSize;

    /**
     * 主节点连接池大小）默认值：64
     */
    private int masterConnPoolSize;

    /**
     * 主节点每个从节点的最小空闲连接大小 默认值：24
     */
    private int masterConnMinIdleSize;
}
