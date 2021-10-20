/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.dmj.cli.config;

import com.dmj.cli.common.exception.AdminException;
import com.dmj.cli.mapper.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据库配置
 */
@Configuration
public class DbConfig {

    @Value("${cli.database}")
    private String database;

    @Autowired
    private MySQLGeneratorDao mySQLGeneratorDao;

    @Autowired
    private OracleGeneratorDao oracleGeneratorDao;

    @Autowired
    private SQLServerGeneratorDao sqlServerGeneratorDao;
    
    @Autowired
    private PostgreSQLGeneratorDao postgreSQLGeneratorDao;


    @Bean
    @Primary
    public GeneratorDao getGeneratorDao() {
        switch (database) {
            case "mysql":
                return mySQLGeneratorDao;
            case "oracle":
                return oracleGeneratorDao;
            case "sqlserver":
                return sqlServerGeneratorDao;
            case "postgresql":
                return postgreSQLGeneratorDao;
            default:
                throw new AdminException("不支持当前数据库：" + database);
        }
    }

}
