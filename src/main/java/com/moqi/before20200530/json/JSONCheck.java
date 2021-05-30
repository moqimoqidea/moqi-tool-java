package com.moqi.before20200530.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import lombok.extern.slf4j.Slf4j;

/**
 * JSON 工具类
 *
 * Created by moqi
 * On 4/21/21 14:18
 */
@Slf4j
public class JSONCheck {

    public static void main(String[] args) {

        boolean validJSON = isValidJSON("123");
        System.out.println("validJSON = " + validJSON);

        boolean validJSON1 = isValidJSON("");
        System.out.println("validJSON1 = " + validJSON1);

        boolean validJSON2 = isValidJSON(null);
        System.out.println("validJSON2 = " + validJSON2);

        boolean validJSONArray = isValidJSONArray("");
        System.out.println("validJSONArray = " + validJSONArray);

        boolean validJSONArray1 = isValidJSONArray("[]");
        System.out.println("validJSONArray1 = " + validJSONArray1);

        boolean validJSONArray2 = isValidJSONArray(null);
        System.out.println("validJSONArray2 = " + validJSONArray2);

    }

    public static boolean isValidJSON(String string) {
        try {
            JSON.parseObject(string);
        } catch (JSONException exception) {
            return false;
        }

        return true;
    }

    public static boolean isNotJSON(String string) {
        return !isValidJSON(string);
    }

    public static boolean isValidJSONArray(String string) {
        try {
            JSON.parseArray(string);
        } catch (JSONException exception) {
            return false;
        }

        return true;
    }

    public static boolean isNotJSONArray(String string) {
        return !isValidJSONArray(string);
    }

}
