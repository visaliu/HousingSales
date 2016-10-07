package com.visa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by visa on 2016/10/6.
 */
@Service
public class ServerTest {

    @Autowired
    private TestDao testDao;

    public void test1(){
        System.out.println("test 1 excute");
    }

    public void test2(){
        TestEntity testEntity=new TestEntity("visa");
        testDao.save(testEntity);
    }
}
