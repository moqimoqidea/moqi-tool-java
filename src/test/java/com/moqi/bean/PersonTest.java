package com.moqi.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.junit.jupiter.api.Test;

/**
 * @author moqi
 * On 3/4/20 10:49
 */
class PersonTest {

    /**
     * 1.2.67 版本的 fastjson 解析异常
     */
    @Test
    public void parse() {
        String line = "{\"name\":5, \"address\":\"beijing\", \"age\": 10}";

        Person bean = JSON.parseObject(line, Person.class, Feature.InitStringFieldAsEmpty);
        System.out.println("bean = " + bean);
    }

}