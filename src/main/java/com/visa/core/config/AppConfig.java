package com.visa.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Created by visa on 2016/10/6.
 */
@Configuration
@ComponentScan(basePackages = "com.visa")
@EnableAspectJAutoProxy(proxyTargetClass=true)

public class AppConfig {

}
