package com.moqi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee Bean
 *
 * @author moqi On 2/19/21 14:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String name;
    private int age;

}
