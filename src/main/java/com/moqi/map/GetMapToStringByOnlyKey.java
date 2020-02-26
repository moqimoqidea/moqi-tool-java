package com.moqi.map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 获取 Map.toString() 固定格式中固定 Key 对应的值
 *
 * @author moqi
 * On 2/25/20 19:20
 */

public class GetMapToStringByOnlyKey {

    public static final String CHANNEL = "channel=";
    private static final String REGEX_SUFFIX = CHANNEL + "(.*?,|.*?$)";
    public static final Pattern PATTERN_COMPILE = Pattern.compile(REGEX_SUFFIX);

    public static void main(String[] args) {
        String s = "{other={newsid=imxxstf3273293-comos-finance-cms, expid=15825122309522sinawap5e533866e8784800793870, pageCategory=yiqing, channel=yiqing_shanghai}, channel=, name=CL_R_1, seId=049c2fce06}";

        String substring = s.substring(s.lastIndexOf("{") + 1, s.indexOf("}"));
        System.out.println("substring = " + substring);

        Matcher matcher = PATTERN_COMPILE.matcher(substring);

        if (matcher.find()) {
            // 匹配值去掉多余部分
            String value = matcher.group().replace(CHANNEL, "").replace(",", "");

            System.out.println("value = " + value);
        }
    }

}
