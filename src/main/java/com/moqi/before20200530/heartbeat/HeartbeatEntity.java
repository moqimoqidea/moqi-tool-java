package com.moqi.before20200530.heartbeat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author moqi
 * On 3/8/21 15:41
 */

public class HeartbeatEntity implements Serializable {

    private long time;
    private String nodeID;
    private String error;
    private Map<String, Object> info = new HashMap<>();

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
