package com.visa.demo;

import javax.persistence.*;

/**
 * Created by visa on 2016/10/7.
 */

@Entity
@Table(name="test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    public TestEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
