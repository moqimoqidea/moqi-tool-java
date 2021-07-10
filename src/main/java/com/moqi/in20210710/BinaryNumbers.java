package com.moqi.in20210710;

/**
 * Binary Numbers in Java: https://www.baeldung.com/java-binary-numbers
 *
 * @author moqi
 * On 2021/7/10 22:41
 */
public class BinaryNumbers {

    /**
     * 十进制转二进制
     *
     * @param decimalNumber
     * @return binaryNumberString
     */
    public static String convertDecimalToBinaryString(Integer decimalNumber) {
        if (decimalNumber == 0) {
            return "0";
        }

        StringBuilder binaryNumber = new StringBuilder();
        int quotient = decimalNumber;

        while (quotient > 0) {
            binaryNumber.append(quotient % 2);
            quotient /= 2;
        }

        binaryNumber.reverse();
        return binaryNumber.toString();
    }

    /**
     * 二进制转十进制
     *
     * 只处理简单情况，未处理负数等复杂情况
     *
     * @param binaryNumberString
     * @return decimalNumber
     */
    public static Integer convertBinaryToDecimalString(String binaryNumberString) {
        int length = binaryNumberString.length();
        int decimal = 0;

        for (int i = 0; i < length; i++) {
            int base = Integer.parseInt(String.valueOf(binaryNumberString.charAt(i)));
            double pow = Math.pow(2.0, length - 1 - i);
            decimal += base * pow;
        }

        return decimal;
    }

}
