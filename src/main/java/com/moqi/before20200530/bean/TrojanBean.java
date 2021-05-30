package com.moqi.before20200530.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author moqi
 * On 2/23/20 10:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrojanBean {

    private int localPort;
    private boolean shareOverLan;
    private List<ConfigsBean> configs;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConfigsBean {

        private String password;
        private String remarks;
        private String server;
        private int server_port;
        private boolean tcp_fast_open;
        private boolean verify;

    }
}
