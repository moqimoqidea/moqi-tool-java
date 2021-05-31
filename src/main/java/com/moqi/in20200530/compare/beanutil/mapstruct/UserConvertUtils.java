package com.moqi.in20200530.compare.beanutil.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 转换类
 *
 * @author moqi
 * On 2021/5/31 00:59
 */
@Mapper
public interface UserConvertUtils {

    UserConvertUtils INSTANCE = Mappers.getMapper(UserConvertUtils.class);

    /**
     * 普通的映射
     *
     * @param userDO UserDO数据持久层类
     * @return 数据传输类
     */
    UserDTO doToDTO(UserDO userDO);

    /**
     * 类型转换的映射
     *
     * @param userDO UserDO数据持久层类
     * @return 数据传输类
     */
    @Mappings({
            @Mapping(target = "gmtBroth", source = "gmtBroth", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "balances", source = "balance")
    })
    UserDTO doToDtoWithConvert(UserDO userDO);

    /**
     * 多对一映射
     *
     * @param userDO
     * @param userInfoDO
     * @return
     */
    UserDTO doAndInfoToDto(UserDO userDO, UserInfoDO userInfoDO);
}
