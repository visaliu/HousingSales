package com.visa.demo;

import com.visa.demo.entity.TestEntity;
import com.visa.demo.repositories.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by visa on 2016/10/6.
 */
@Service
@Transactional
public class ServerTest {

    @Autowired
    private TestDao testDao;

    public void test1(){
        System.out.println("test 1 excute");
    }


    public void test2(){
        TestEntity testEntity=new TestEntity();
        testEntity.setName("hahah");
        testDao.save(testEntity);
    }
}
