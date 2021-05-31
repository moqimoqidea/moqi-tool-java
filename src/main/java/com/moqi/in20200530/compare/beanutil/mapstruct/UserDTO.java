package com.moqi.in20200530.compare.beanutil.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDTO
 *
 * @author moqi
 * On 2021/5/31 01:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String userName;
    private String gmtBroth;
    private String balances;
    private Integer sex;

}

