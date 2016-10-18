package com.visa.demo;

import com.visa.demo.entity.TestEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by visa on 2016/10/7.
 */

@Repository
public interface TestDao extends PagingAndSortingRepository<TestEntity, Integer>{
}
