package com.moqi.before20200530.heartbeat;

/**
 * @author moqi
 * On 3/8/21 15:44
 */
public interface HeartbeatHandler {

    Cmder sendHeartBeat(HeartbeatEntity info);

}
