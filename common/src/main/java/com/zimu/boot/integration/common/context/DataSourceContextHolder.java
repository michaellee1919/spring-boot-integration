package com.zimu.boot.integration.common.context;

import com.zimu.boot.integration.common.enums.DataSourceEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceEnum> context = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    /**
     * 赋值
     *
     * @param datasourceType
     */
    public static void set(DataSourceEnum datasourceType) {
        context.set(datasourceType);
    }

    /**
     * 获取值
     * @return
     */
    public static DataSourceEnum get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }

    public static void master() {
        set(DataSourceEnum.MASTER);
    }

    public static void slave() {
        //  轮询
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DataSourceEnum.SLAVE1);
        }else {
            set(DataSourceEnum.SLAVE2);
        }
    }
}
