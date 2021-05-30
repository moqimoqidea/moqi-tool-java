package com.moqi.before20200530.json;

import com.alibaba.fastjson.JSON;
import com.moqi.before20200530.bean.ShadowSocksBean;
import com.moqi.before20200530.bean.TrojanBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author moqi
 * On 2/23/20 10:33
 */

public class TransferJson {

    private static final String SOURCE_PATH = "/Users/moqi/Downloads/Shadowrocket.json";
    private static final String TARGET_PATH = "/Users/moqi/Downloads/gui-config.json";
    public static final String SUBSCRIBE_TYPE = "Subscribe";

    public static void main(String[] args) throws IOException {

        String sourceJSON = new String(Files.readAllBytes(Paths.get(TransferJson.SOURCE_PATH)), Charset.defaultCharset());

        List<ShadowSocksBean> parseArray = JSON.parseArray(sourceJSON, ShadowSocksBean.class);
        List<TrojanBean.ConfigsBean> configs = new ArrayList<>();

        for (final ShadowSocksBean socksBean : parseArray) {

            if (Objects.equals(SUBSCRIBE_TYPE, socksBean.getType())) {
                continue;
            }

            TrojanBean.ConfigsBean configsBean = new TrojanBean.ConfigsBean();
            configsBean.setPassword(socksBean.getPassword());

            configsBean.setServer(socksBean.getHost());
            configsBean.setRemarks(socksBean.getTitle());
            configsBean.setServer_port(socksBean.getPort());
            configsBean.setTcp_fast_open(true);
            configsBean.setVerify(true);

            configs.add(configsBean);
        }

        TrojanBean bean = new TrojanBean();
        bean.setLocalPort(1086);
        bean.setShareOverLan(false);
        bean.setConfigs(configs);

        String s = JSON.toJSONString(bean, true);
        System.out.println(s);

        try (PrintWriter out = new PrintWriter(TransferJson.TARGET_PATH)) {
            out.println(s);
        }
    }

}
