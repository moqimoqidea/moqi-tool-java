package com.moqi.in20200530.beanutil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author moqi
 * On 2021/5/30 20:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CopyEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    private AgeEnum age;

    private Date date;

}
