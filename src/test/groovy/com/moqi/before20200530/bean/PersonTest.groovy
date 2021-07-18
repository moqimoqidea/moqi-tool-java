package com.moqi.before20200530.bean

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.parser.Feature
import spock.lang.Specification

/**
 * 1.2.67 版本的 fastjson 解析异常
 *
 * @author moqi* On 2021/7/9 02:04
 */
class PersonTest extends Specification{

    def "test fastjson parse wrong"() {
        when:
        String line = "{\"name\":5, \"address\":\"beijing\", \"age\": 10}"
        Person bean = JSON.parseObject(line, Person.class, Feature.InitStringFieldAsEmpty)
        System.out.println("bean = " + bean)

        then:
        1 == 1
    }

}
