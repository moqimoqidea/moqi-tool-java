package com.moqi.in20200530.compare.beanutil.mapstruct;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 造数据工具类
 *
 * @author moqi
 * On 2021/5/31 00:59
 */
public class DataUtil {

    /**
     * 模拟查询出一条数据
     *
     * @return UserDO
     */
    public static UserDO createData() {
        return new UserDO(1, "Van", LocalDateTime.now(), new BigDecimal(100L));
    }

    /**
     * 模拟查询出多条数据
     *
     * @param num 数量
     * @return List<UserDO>
     */
    public static List<UserDO> createDataList(int num) {
        List<UserDO> userDoList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            UserDO userDO = new UserDO(i + 1, "Van", LocalDateTime.now(), new BigDecimal(100L));
            userDoList.add(userDO);
        }
        return userDoList;
    }
}
