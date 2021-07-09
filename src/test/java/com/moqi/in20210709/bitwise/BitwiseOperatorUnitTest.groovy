package com.moqi.in20210709.bitwise

import spock.lang.Specification

/**
 * https://www.baeldung.com/java-bitwise-operators
 *
 * @author moqi* @create 7/9/21 14:11
 */
class BitwiseOperatorUnitTest extends Specification{

    def "given two integers when and operator then new decimal number"() {
        given:
        def value1 = 6
        def value2 = 5

        when:
        def result = value1 & value2

        then:
        4 == result
    }

}
