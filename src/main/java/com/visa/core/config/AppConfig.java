package com.visa.core.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * Created by visa on 2016/10/6.
 */
@Configuration
@ComponentScan(basePackages = "com.visa")
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Import({DBConfig.class})
public class AppConfig {

}
