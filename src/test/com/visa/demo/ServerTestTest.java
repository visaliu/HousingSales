package com.visa.demo;

import com.visa.core.config.AppConfig;
import com.visa.core.config.DBConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by visa on 2016/10/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DBConfig.class})
@PropertySource("classpath:tt.properties")
public class ServerTestTest {

    @Value("${tt.t1}")
    String t1;

    @Autowired
    private ServerTest serverTest;

    @Test
    public void testTest1() throws Exception {
        serverTest.test1();
    }

    @Test
    public void testTest2() throws Exception {
        serverTest.test2();
    }
}