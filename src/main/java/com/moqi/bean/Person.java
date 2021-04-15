package com.moqi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moqi
 * On 3/4/20 10:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String address;
    private String id;
    private int age;

}
