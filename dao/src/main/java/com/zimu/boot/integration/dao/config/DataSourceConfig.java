package com.zimu.boot.integration.dao.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zimu.boot.integration.common.enums.DataSourceEnum;
import com.zimu.boot.integration.dao.router.DataSourceRouter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.zimu.boot.integration.dao.mapper", sqlSessionTemplateRef = "sqlTemplate")
public class DataSourceConfig {

    /**
     * 主库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 从库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slaver1() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 从库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slaver2() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 实例化数据源路由
     */
    @Bean
    public DataSourceRouter dynamicDB(@Qualifier("master") DataSource masterDataSource,
                                      @Autowired(required = false) @Qualifier("slaver1") DataSource slave1DataSource,
                                      @Autowired(required = false) @Qualifier("slaver2") DataSource slave2DataSource) {
        DataSourceRouter dynamicDataSource = new DataSourceRouter();
        Map<Object, Object> targetDataSources = new HashMap<>(3);
        targetDataSources.put(DataSourceEnum.MASTER, masterDataSource);
        targetDataSources.put(DataSourceEnum.SLAVE1, slave1DataSource);
        targetDataSources.put(DataSourceEnum.SLAVE2, slave2DataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }



}
