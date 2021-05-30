package com.moqi.in20200530.beanutil;

import java.util.Arrays;

/**
 * @author moqi
 * On 2021/5/30 20:24
 */
public enum AgeEnum {

    ZERO(0),
    TEN(10),
    EIGHTEEN(18),
    TWENTY(20);

    private final int value;

    AgeEnum(final int value) {
        this.value = value;
    }

    public static AgeEnum parseValue(int value) {
        return Arrays.stream(AgeEnum.values())
                .filter(x -> x.getValue() == value)
                .findFirst()
                .orElse(null);
    }

    public int getValue() {
        return value;
    }

}
