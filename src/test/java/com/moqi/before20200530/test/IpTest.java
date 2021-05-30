package com.moqi.before20200530.test;

import com.google.common.net.InetAddresses;

/**
 * Created by wenbo17
 * On 5/17/21 10:55
 */
public class IpTest {

    public static void main(String[] args) {

        boolean inetAddress = InetAddresses.isInetAddress("10.77.120.250");
        System.out.println("inetAddress = " + inetAddress);
        // boolean inetAddress1 = InetAddresses.isInetAddress(null);
        // System.out.println("inetAddress1 = " + inetAddress1);
        boolean inetAddress2 = InetAddresses.isInetAddress("");
        System.out.println("inetAddress2 = " + inetAddress2);

    }

}
