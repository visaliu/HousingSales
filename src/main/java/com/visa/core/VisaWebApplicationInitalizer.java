package com.visa.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Created by visa on 2016/10/6.
 */
public class VisaWebApplicationInitalizer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //添加监听器
        servletContext.setInitParameter("log4jConfigLocation", "classpath:resources/log4j.properties");
        servletContext.addListener(Log4jConfigListener.class);

        //添加拦截器
        OpenSessionInViewFilter hibernateSession=new OpenSessionInViewFilter(); //实现Hibernate的延迟加载功能。基于一个请求一个hibernate session的原则。
        FilterRegistration.Dynamic filterRegistration=servletContext.addFilter("hibernateFilter", hibernateSession);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/");



    }
}
