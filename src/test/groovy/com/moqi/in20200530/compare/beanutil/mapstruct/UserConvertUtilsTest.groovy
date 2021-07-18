package com.moqi.in20200530.compare.beanutil.mapstruct

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * @author moqi* On 2021/7/9 02:02
 */
@Slf4j
class UserConvertUtilsTest extends Specification {

    // 一般拷贝
    def "normalCopy"() {
        when:
        def userDO = DataUtil.createData()
        log.info("拷贝前：userDO:{}", userDO)
        UserDTO userDTO = UserConvertUtils.INSTANCE.doToDTO(userDO)
        log.info("拷贝后：userDTO:{}", userDTO)

        then:
        1 == 1
    }

    // 包含类型转换的拷贝
    def "doToDtoWithConvert"() {
        when:
        UserDO userDO = DataUtil.createData()
        log.info("拷贝前：userDO:{}", userDO)
        UserDTO userDTO = UserConvertUtils.INSTANCE.doToDtoWithConvert(userDO)
        log.info("拷贝后：userDTO:{}", userDTO)

        then:
        1 == 1
    }

// 包含类型转换的拷贝
    def "doAndInfoToDto"() {
        when:
        UserDO userDO = DataUtil.createData()
        log.info("拷贝前：userDO:{}", userDO)
        UserDTO userDTO = UserConvertUtils.INSTANCE.doAndInfoToDto(userDO, new UserInfoDO(1))
        log.info("拷贝后：userDTO:{}", userDTO)
        then:
        1 == 1
    }


}
