package com.visa.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.sun.tools.corba.se.idl.InterfaceGen;
import com.sun.tools.javah.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * 资源文件加载类
 *
 * Created by visa on 2016/10/7.
 */
@Configuration
@PropertySources(
    @PropertySource("classpath:db.properties")
)
public class ResourcesLoader {
     private static final Logger logger=Logger.getLogger(ResourcesLoader.class);


/***********************************数据源配置项******************************************/
    @Value("${db.driver}")
    String driverClass;
    @Value("${db.url}")
    String url;
    @Value("${db.username}")
    String userName;
    @Value("${db.password}")
    String passWord;

    @Value("${db.maxActive}")
    String maxActive;
    @Value("${db.validationQuery}")
    String validationQuery;
    @Value("${db.filters}")
    String filters;



    @Bean(name="dataSource")
    public DataSource dataSource(){
        logger.info("DataSource");
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        dataSource.setMaxActive(Integer.valueOf(maxActive));
        dataSource.setValidationQuery(validationQuery);
        try{
            dataSource.setFilters(filters);
        }catch (SQLException e){
            logger.warn(e.getMessage());
        }
        return dataSource;
    }

  /*********************************缓存配置项*********************************************/


}
