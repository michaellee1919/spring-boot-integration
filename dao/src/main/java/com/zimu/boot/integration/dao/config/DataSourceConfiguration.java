package com.zimu.boot.integration.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.zimu.boot.integration.dao.mapper"})
public class DataSourceConfiguration {
}
