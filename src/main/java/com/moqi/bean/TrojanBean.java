package com.moqi.bean;

import java.util.List;

/**
 * @author moqi
 * On 2/23/20 10:32
 */

public class TrojanBean {


    private int localPort;
    private boolean shareOverLan;
    private List<ConfigsBean> configs;

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public boolean isShareOverLan() {
        return shareOverLan;
    }

    public void setShareOverLan(boolean shareOverLan) {
        this.shareOverLan = shareOverLan;
    }

    public List<ConfigsBean> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ConfigsBean> configs) {
        this.configs = configs;
    }

    public static class ConfigsBean {

        private String password;
        private String remarks;
        private String server;
        private int server_port;
        private boolean tcp_fast_open;
        private boolean verify;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public int getServer_port() {
            return server_port;
        }

        public void setServer_port(int server_port) {
            this.server_port = server_port;
        }

        public boolean isTcp_fast_open() {
            return tcp_fast_open;
        }

        public void setTcp_fast_open(boolean tcp_fast_open) {
            this.tcp_fast_open = tcp_fast_open;
        }

        public boolean isVerify() {
            return verify;
        }

        public void setVerify(boolean verify) {
            this.verify = verify;
        }
    }
}
