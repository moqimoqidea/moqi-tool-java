package com.moqi.in20200530.beanutil;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学习 BeanUtils 功能
 *
 * @author moqi
 * On 2021/5/30 17:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    private List<Employee> subordinate;

    private Map<String, Address> address;

    private Integer age;

    private Date date;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;

    }

}


