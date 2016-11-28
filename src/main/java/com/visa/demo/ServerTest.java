package com.visa.demo;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.visa.demo.entity.TestEntity;
import com.visa.demo.repositories.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by visa on 2016/10/6.
 */
@Service
public class ServerTest {

    @Autowired
    private TestDao testDao;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Autowired
    private PlatformTransactionManager transactionManager;


    public void test1(){
        System.out.println("test 1 excute");
    }

   @Transactional(rollbackFor=Exception.class)
  // @Transactional
    public void test2() throws Exception{
        TestEntity testEntity=new TestEntity();
        testEntity.setName("haha");
        testDao.save(testEntity);

        TestEntity testEntity2=new TestEntity();
        testEntity2.setName("hehe");
        testDao.save(testEntity2);

        throw new Exception("抛个异常");
    }

    public List<TestEntity> findAll(){

        return  testDao.findAll();

    }

    //JPA Specification 查询
    public List<User> findAll2(){
        //JPQL:select u.old,u.name form User u left join u.interests where u.old>20 or u.workTime>1
        EntityManager entityManager=entityManagerFactory.createEntityManager();

        CriteriaBuilder cb =entityManager.getCriteriaBuilder(); //查询对象构造器
        CriteriaQuery cq=cb.createQuery(); //查询对象

        Root<User> root=cq.from(User.class);//查询主体表
        root.join("interests", JoinType.LEFT);// 查询关联表
        Fetch footFetch=root.fetch("interests");//fetch模式
        cq.select(root); //查询所有字段
        cq.select(cb.construct(User.class,root.get("old"),root.get("name"))); //只查询old和name字段，并构造成User对象
        Predicate pre=cb.greaterThan(root.get("old").as(Integer.class),cb.parameter(Integer.class,"old"));  //构造查询条件
        Predicate pre1=cb.greaterThan(root.get("workTime").as(Integer.class),cb.parameter(Integer.class,"workTime"));
        cq.where(cb.or(pre,pre1));  //组装查询条件
        cq.orderBy(cb.desc(root.get("old")),cb.asc(root.get("workTime")));
        Query query=entityManager.createQuery(cq);
        query.setParameter("old",20);
        query.setParameter("workTime",1);

        List<User> users=query.getResultList();
    }


    //JPA Specification 查询
    public List<TestEntity> findAll3(){
             return testDao.findAll(new Specification<TestEntity>(){
                 @Override
                 public Predicate toPredicate(Root<TestEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                     List<Predicate> preList=new ArrayList<Predicate>();
                     Predicate predicate=cb.like(root.get("name").as(String.class),"%visa&");
                     preList.add(predicate);
                     return cq.where(preList.toArray(new Predicate[preList.size()])).getRestriction();
                 }
             });

    }


    public void test3() throws Exception {
        //事务开始
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setTimeout(30);
        //事务状态
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            TestEntity testEntity=new TestEntity();
            testEntity.setName("test33");
            testDao.save(testEntity);
          //  transactionManager.commit(status);
            throw new Exception("抛个异常");
        } catch (Exception e) {
            if(!status.isCompleted())
              transactionManager.rollback(status);
            throw new Exception(e.getMessage());
        }


    }


}
