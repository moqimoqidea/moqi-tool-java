package com.moqi.before20200530.json;

import com.alibaba.fastjson.JSON;
import com.moqi.before20200530.bean.ShadowSocksBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 单次使用，将 Shadowrocket.json 中 title 信息完善，使之带上消费速率
 *
 * @author moqi
 * On 2/23/20 10:33
 */

public class UpdateJson {

    public static final String SUBSCRIBE_TYPE = "Subscribe";
    private static final String SOURCE_PATH = "/Users/moqi/Downloads/Shadowrocket.json";
    private static final String TARGET_PATH = "/Users/moqi/Downloads/Shadowrocket-update.json";

    public static void main(String[] args) throws IOException {

        String sourceJSON = new String(Files.readAllBytes(Paths.get(UpdateJson.SOURCE_PATH)), Charset.defaultCharset());

        List<ShadowSocksBean> parseArray = JSON.parseArray(sourceJSON, ShadowSocksBean.class);
        List<ShadowSocksBean> resultArray = new ArrayList<>();

        for (final ShadowSocksBean socksBean : parseArray) {

            if (!Objects.equals(SUBSCRIBE_TYPE, socksBean.getType())) {
                String host = socksBean.getHost();
                String prefixTitle;
                String title;

                if (host.startsWith("v")) {
                    prefixTitle = host.substring(0, 4);
                } else {
                    prefixTitle = host.substring(0, 5);
                }

                if (prefixTitle.startsWith("v6-1") || prefixTitle.startsWith("ee1-") || prefixTitle.startsWith("fm2-")) {
                    title = prefixTitle + "-0.1";
                } else if (prefixTitle.startsWith("hk1-") || prefixTitle.startsWith("hk5-")) {
                    title = prefixTitle + "-0.5";
                } else {
                    title = prefixTitle + "-1.0";
                }

                socksBean.setTitle(title);
            }

            resultArray.add(socksBean);
        }

        String s = JSON.toJSONString(resultArray, true);
        System.out.println(s);

        try (PrintWriter out = new PrintWriter(UpdateJson.TARGET_PATH)) {
            out.println(s);
        }
    }

}
