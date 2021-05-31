package com.moqi.in20200530.compare.beanutil.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 源对象属性类
 *
 * @author moqi
 * On 2021/5/31 00:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {

    private int id;
    private String userName;

    /**
     * 以下两个字段用户模拟自定义转换
     */
    private LocalDateTime gmtBroth;
    private BigDecimal balance;

}
