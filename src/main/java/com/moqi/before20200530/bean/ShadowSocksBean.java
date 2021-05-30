package com.moqi.before20200530.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moqi
 * On 2/23/20 10:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShadowSocksBean {

    private String obfsParam;
    private int weight;
    private String title;
    private String host;
    private String file;
    private String uuid;
    private String method;
    private String flag;
    private double updated;
    private String obfs;
    private String chain;
    private String type;
    private String cert;
    private String plugin;
    private String user;
    private String protoParam;
    private int port;
    private String proto;
    private String peer;
    private String password;
    private String data;
    private int ping;
    private double created;
}
