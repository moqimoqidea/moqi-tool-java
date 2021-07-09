package com.moqi.in20210709.bitwise

import spock.lang.Specification

/**
 * https://www.baeldung.com/java-bitwise-operators
 *
 * Bitwise Operator Table
 *
 * A	B	A|B	A&B	A^B	~A
 * 0	0	0	0	0	1
 * 1	0	1	0	1	0
 * 0	1	1	0	1	1
 * 1	1	1	1	0	0
 *
 * 参考 https://groovy-lang.org/operators.html#_bitwise_operators
 * groovy 位运算必须加括号
 *
 * 进制转换: https://www.sojson.com/hexconvert.html
 *
 * @author moqi* @create 7/9/21 14:11
 */
class BitwiseOperatorUnitTest extends Specification {

    /**
     * 0110
     * 0101
     * -----
     * 0100
     */
    def "given #value1, #value2 when and operator then get #result"() {
        expect:
        result == (value1 & value2)

        where:
        value1 | value2 | result
        0b0110 | 0b0101 | 0b0100
        06     | 05     | 04
        6      | 5      | 4
        0x6    | 0x5    | 0x4
    }

    /**
     * 0110
     * 0101
     * -----
     * 0111
     */
    def "given #value1, #value2 when or operator then get #result"() {
        expect:
        result == (value1 | value2)

        where:
        value1 | value2 | result
        0b0110 | 0b0101 | 0b0111
        06     | 05     | 07
        6      | 5      | 7
        0x6    | 0x5    | 0x7
    }

    /**
     * 0110
     * 0101
     * -----
     * 0011
     */
    def "given #value1, #value2 when xor operator then get #result"() {
        expect:
        result == (value1 ^ value2)

        where:
        value1 | value2 | result
        0b0110 | 0b0101 | 0b0011
        06     | 05     | 03
        6      | 5      | 3
        0x6    | 0x5    | 0x3
    }

    /**
     * 0000 0110 -> 1111 1001
     *
     * leftmost bit is 1 means the sign is negative.
     *
     * 1111 1001 -> 0000 0110 + 1 -> 0000 0111
     */
    def "given #value1 then not operator then get #result"() {
        expect:
        result == (~value1)

        where:
        value1 | result
        0b0110 | -0b0111
        06     | -07
        6      | -7
        0x6    | -0x7
    }

    def "given #value1 when signed right shift operator with #rightShiftNumber then get #result"() {
        expect:
        result == (value1 >> rightShiftNumber)

        where:
        value1  | rightShiftNumber | result
        0b1100  | 0b010            | 0b011
        014     | 02               | 03
        12      | 2                | 3
        0xc     | 0x2              | 0x3
        -0b1100 | 0b010            | -0b011
        -014    | 02               | -03
        -12     | 2                | -3
        -0xc    | 0x2              | -0x3
    }

    def "given #value1 when signed left shift operator with #rightShiftNumber then get #result"() {
        expect:
        result == (value1 << rightShiftNumber)

        where:
        value1  | rightShiftNumber | result
        0b1100  | 0b010            | 0b110000
        014     | 02               | 060
        12      | 2                | 48
        0xc     | 0x2              | 0x30
        -0b1100 | 0b010            | -0b110000
        -014    | 02               | -060
        -12     | 2                | -48
        -0xc    | 0x2              | -0x30
    }

    def "given #value1 when unsigned left shift operator with #rightShiftNumber then get #result"() {
        expect:
        result == (value1 >>> rightShiftNumber)

        where:
        value1  | rightShiftNumber | result
        0b1100  | 0b010            | 0b011
        014     | 02               | 03
        12      | 2                | 3
        0xc     | 0x2              | 0x3
        -0b1100 | 0b010            | 0b111111111111111111111111111101
        -014    | 02               | 07777777775
        -12     | 2                | 1073741821
        -0xc    | 0x2              | 0x3ffffffd
    }

}
