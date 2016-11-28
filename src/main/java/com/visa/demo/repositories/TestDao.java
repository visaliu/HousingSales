package com.visa.demo.repositories;

import com.visa.demo.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by visa on 2016/10/7.
 */
@Repository
//public interface TestDao extends PagingAndSortingRepository<TestEntity, Integer> {
public interface TestDao extends JpaRepository<TestEntity, Integer>,JpaSpecificationExecutor<TestEntity> {
}
