package com.moqi.in20210710

import spock.lang.Specification

/**
 * Binary Numbers in Java: https://www.baeldung.com/java-binary-numbers
 *
 * @author moqi* On 2021/7/10 22:42
 */
class BinaryNumbersTest extends Specification {

    def "given binary #value then return #result"() {
        expect:
        result == value

        where:
        value     | result
        0b101     | 5
        0b11      | 3
        0b1001    | 9
        0B11101   | 29
        -0B100101 | -37
    }

    def "given decimal number #value then convert to binary number #result"() {
        expect:
        result == value

        where:
        value                                          | result
        Integer.toBinaryString(8)                      | "1000"
        Integer.toBinaryString(20)                     | "10100"
        BinaryNumbers.convertDecimalToBinaryString(8)  | "1000"
        BinaryNumbers.convertDecimalToBinaryString(20) | "10100"
    }

    def "given binary number #value then convert to decimal number #result"() {
        expect:
        result == value

        where:
        value   | result
        0b1000  | Integer.parseInt("1000", 2)
        0b10100 | Integer.parseInt("10100", 2)
        0b1000  | BinaryNumbers.convertBinaryToDecimalString("1000")
        0b10100 | BinaryNumbers.convertBinaryToDecimalString("10100")
    }

}
