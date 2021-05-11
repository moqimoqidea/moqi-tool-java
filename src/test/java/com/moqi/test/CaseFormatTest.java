package com.moqi.test;

import com.google.common.base.CaseFormat;

/**
 * https://www.tutorialspoint.com/guava/guava_caseformat.htm
 *
 * Created by wenbo17
 * On 5/11/21 11:11
 */
public class CaseFormatTest {

    /**
     * testData
     * testData
     * TestData
     */
    public static void main(String[] args) {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
    }

}

