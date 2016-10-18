package com.visa.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.sun.tools.corba.se.idl.InterfaceGen;
import com.sun.tools.hat.internal.parser.Reader;
import com.sun.tools.javah.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;


/**
 * 资源文件加载类
 *
 * Created by visa on 2016/10/7.
 */

@Configuration
@PropertySource({"classpath:db.properties",""})
public class ResourcesLoader {

    @Resource
    private Environment environment;

    private static final Logger logger=Logger.getLogger(ResourcesLoader.class);


    /***数据源配置项**********/
    @Bean(name="dataSource")
    public DataSource dataSource() throws Exception{
        logger.info("DataSource");

        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        dataSource.setMaxActive(Integer.valueOf(environment.getProperty("db.maxActive")));
        dataSource.setValidationQuery(environment.getProperty("db.validationQuery"));
        try{
            dataSource.setFilters(environment.getProperty("db.filters"));
        }catch (SQLException e){
            logger.warn(e.getMessage());
        }
        return dataSource;
    }

    /**********缓存配置项***********/


}
