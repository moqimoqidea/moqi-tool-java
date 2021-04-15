package com.moqi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
/**
 * @ Description：用户信息
 * @ Author     ：xiaojiang
 * @ Date       ：Created in 2019-06-19
 * @ Version    ：0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Userinfo {
    private String username;
    private String gender;
    private Integer age;
    private Date birthday;
    private List<String> address;
    private Long createTime;

}
