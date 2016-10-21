package com.visa.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by visa on 2016/10/7.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import({ResourcesLoader.class})
@EnableJpaRepositories(basePackages = "com.visa.**.repositories")

public class DBConfig {


    @Resource(name="dataSource")
    private DataSource dataSource;


    //事务管理
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory)
    {
        JpaTransactionManager jpaTransactionManager= new JpaTransactionManager(entityManagerFactory.getObject());

        return  jpaTransactionManager;
    }


    //JAP容器
    @Bean(name ="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter=new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.show_sql","true");
        jpaProperties.put("hibernate.format_sql","true");
        jpaProperties.put("hibernate.hbm2ddl.auto","update");


        LocalContainerEntityManagerFactoryBean entityManagerFactory=new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        /*数据源设置为JTA数据源，事物类型为JTA，如果数据源配置成普通数据源，则事物类型为本地事物：RESOURCE_LOCAL
        entityManagerFactory.setJtaDataSource(dataSource); */

        MutablePersistenceUnitInfo persistenceUnitInfo=new MutablePersistenceUnitInfo();
        persistenceUnitInfo.setTransactionType(PersistenceUnitTransactionType.RESOURCE_LOCAL);

        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.setPackagesToScan("com.visa.**.entity");
        entityManagerFactory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

        return  entityManagerFactory;

    }






    //事务异常处理
    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }



}
