package com.moqi.bean;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 测试 FastJSON 对枚举字段的处理
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Function {

    private FunctionTemplate template;

    public enum FunctionTemplate {

        /**
         * java_http
         */
        JAVA_HTTP(1),

        /**
         * python_http
         */
        PYTHON_HTTP(2),

        /**
         * java
         */
        JAVA(3),

        /**
         * python
         */
        PYTHON(4);

        FunctionTemplate(final int value) {
            this.value = value;
        }

        private final int value;

        public int getValue() {
            return value;
        }

        public static FunctionTemplate parseValue(int value) {
            return Arrays.stream(FunctionTemplate.values())
                    .filter(x -> x.getValue() == value)
                    .findFirst()
                    .orElse(null);
        }

    }

    public static void main(String[] args) {
        Function function = Function.builder().template(FunctionTemplate.JAVA_HTTP).build();
        log.info("function:{}", function);

        String string = JSON.toJSONString(function);
        log.info("string:{}", string);

        Function returnFunction = JSON.parseObject(string, Function.class);
        log.info("returnFunction:{}", returnFunction);
    }

}

