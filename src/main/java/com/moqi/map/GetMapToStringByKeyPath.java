package com.moqi.map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 获取 Map.toString() 固定格式中不固定 KeyPath 对应的值
 * 目前暂不考虑数组
 * 【已解决，子{}串置空】目前的 bug 在于内外层都有某个 KV 对，当 KeyPath 为短路径时，则无法判断 V 的正确性。
 *
 * @author moqi
 * On 2/25/20 19:20
 */
@Slf4j
public class GetMapToStringByKeyPath {


    private static final String EMPTY = "";
    private static final String KEY_REGEX_SUFFIX = "(.*?,|.*?$)";
    private static final String SUB_REGEX_SUFFIX = "\\{.*?}";
    private static final Pattern SUB_REGEX_SUFFIX_COMPILE = Pattern.compile(SUB_REGEX_SUFFIX);

    /**
     * 2020-02-26 10:46:32 INFO  GetMapToStringByKeyPath:47 - prefix:other
     * 2020-02-26 10:46:32 INFO  GetMapToStringByKeyPath:48 - suffix:channel
     * 2020-02-26 10:46:32 INFO  GetMapToStringByKeyPath:51 - trimString:other={newsid=imxxstf3273293-comos-finance-cms, expid=15825122309522sinawap5e533866e8784800793870, pageCategory=yiqing, channel=yiqing_shanghai}, channel=test_value, name=CL_R_1, seId=049c2fce06
     * 2020-02-26 10:46:32 INFO  GetMapToStringByKeyPath:62 - matchValue:other={newsid=imxxstf3273293-comos-finance-cms, expid=15825122309522sinawap5e533866e8784800793870, pageCategory=yiqing, channel=yiqing_shanghai}
     * 2020-02-26 10:46:32 INFO  GetMapToStringByKeyPath:65 - nextSourceString:newsid=imxxstf3273293-comos-finance-cms, expid=15825122309522sinawap5e533866e8784800793870, pageCategory=yiqing, channel=yiqing_shanghai
     * 2020-02-26 10:46:32 INFO  GetMapToStringByKeyPath:77 - trimString:ewsid=imxxstf3273293-comos-finance-cms, expid=15825122309522sinawap5e533866e8784800793870, pageCategory=yiqing, channel=yiqing_shangha
     * 2020-02-26 10:46:32 INFO  GetMapToStringByKeyPath:88 - matchValue:channel=yiqing_shangha
     * 2020-02-26 10:46:32 INFO  GetMapToStringByKeyPath:28 - value:yiqing_shangha
     */
    public static void main(String[] args) {
        String sourceString = "{other={newsid=imxxstf3273293-comos-finance-cms, expid=15825122309522sinawap5e533866e8784800793870, pageCategory=yiqing, channel=yiqing_shanghai}, channel=test_value, name=CL_R_1, seId=049c2fce06}";
        String path = "channel";

        String value = getValueByPath(sourceString, path);
        log.info("value:{}", value);

    }

    /**
     * 根据小数点的路径，逐层拆取需要的字符串和路径
     */
    private static String getValueByPath(String sourceString, String path) {
        if (StringUtils.isBlank(sourceString) || StringUtils.isBlank(path)) {
            return EMPTY;
        }

        if (!path.contains(".")) {
            return getValueByKey(sourceString, path);
        }

        int firstDot = path.indexOf(".");
        String prefix = path.substring(0, firstDot);
        String suffix = path.substring(firstDot + 1);
        log.info("prefix:{}", prefix);
        log.info("suffix:{}", suffix);

        String trimString = trimString(sourceString);
        log.info("trimString:{}", trimString);

        if (Objects.equals(EMPTY, trimString) || Objects.equals(EMPTY, prefix) || Objects.equals(EMPTY, suffix)) {
            return EMPTY;
        }

        String keyWithColon = prefix + "=";
        Matcher matcher = Pattern.compile(keyWithColon + SUB_REGEX_SUFFIX).matcher(trimString);

        if (matcher.find()) {
            String matchValue = matcher.group();
            log.info("matchValue:{}", matchValue);

            String nextSourceString = matchValue.substring(prefix.length() + 2, matchValue.length() - 1);
            log.info("nextSourceString:{}", nextSourceString);
            return getValueByPath(nextSourceString, suffix);
        }

        return EMPTY;
    }

    /**
     * 在最内层的 {k1=v1,k2=v2} 结构里取值
     */
    private static String getValueByKey(String sourceString, String key) {
        String trimString = trimString(sourceString);
        log.info("trimString:{}", trimString);

        if (Objects.equals(EMPTY, trimString)) {
            return EMPTY;
        }

        // 获取只有 K V 的字符串，将由 {} 包含的子串全部替换为空串
        String onlyKVString = SUB_REGEX_SUFFIX_COMPILE.matcher(trimString).replaceAll(EMPTY);
        log.info("onlyKVString:{}", onlyKVString);

        String keyWithColon = key + "=";
        Matcher matcher = Pattern.compile(keyWithColon + KEY_REGEX_SUFFIX).matcher(onlyKVString);

        if (matcher.find()) {
            String matchValue = matcher.group();
            log.info("matchValue:{}", matchValue);
            return matchValue.replace(keyWithColon, EMPTY).replace(",", EMPTY);
        }

        return EMPTY;
    }

    /**
     * 截取字符串，如果长度大于 4 去掉头尾的 { 和 }，否则返回空串
     */
    private static String trimString(String string) {
        int length = string.length();
        return length > 4 ? string.substring(1, length - 1) : EMPTY;
    }

}
