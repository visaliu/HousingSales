package com.visa.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by visa on 2016/10/7.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

}
