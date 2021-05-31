package com.moqi.in20200530.compare.beanutil.mapstruct;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author moqi
 * On 2021/5/31 01:24
 */
@Slf4j
class UserConvertUtilsTest {

    /**
     * 一般拷贝
     */
    @Test
    public void normalCopy() {
        // 模拟查询出数据
        UserDO userDO = DataUtil.createData();
        log.info("拷贝前：userDO:{}", userDO);
        UserDTO userDTO = UserConvertUtils.INSTANCE.doToDTO(userDO);
        log.info("拷贝后：userDTO:{}", userDTO);
    }

    /**
     * 包含类型转换的拷贝
     */
    @Test
    public void doToDtoWithConvert() {
        // 模拟查询出数据
        UserDO userDO = DataUtil.createData();
        log.info("拷贝前：userDO:{}", userDO);
        UserDTO userDTO = UserConvertUtils.INSTANCE.doToDtoWithConvert(userDO);
        log.info("拷贝后：userDTO:{}", userDTO);
    }

    /**
     * 包含类型转换的拷贝
     */
    @Test
    public void doAndInfoToDto() {
        // 模拟查询出数据
        UserDO userDO = DataUtil.createData();
        log.info("拷贝前：userDO:{}", userDO);
        UserDTO userDTO = UserConvertUtils.INSTANCE.doAndInfoToDto(userDO, new UserInfoDO(1));
        log.info("拷贝后：userDTO:{}", userDTO);
    }

}
