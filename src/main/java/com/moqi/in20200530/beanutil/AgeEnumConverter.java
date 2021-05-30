package com.moqi.in20200530.beanutil;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * AgeEnum 转换器
 *
 * @author moqi
 * On 2021/5/30 20:25
 */
public class AgeEnumConverter extends AbstractConverter {

    @Override
    protected <T> T convertToType(Class<T> type, Object value) {
        if (!(value instanceof Integer)) {
            throw new RuntimeException("value type is not integer");
        }

        return type.cast(AgeEnum.parseValue((int) value));
    }

    @Override
    protected Class<?> getDefaultType() {
        return AgeEnum.class;
    }


}
