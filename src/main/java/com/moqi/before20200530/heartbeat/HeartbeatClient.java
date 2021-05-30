package com.moqi.before20200530.heartbeat;

import com.moqi.before20200530.rpc.RPCClient;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * @author moqi
 * On 3/8/21 15:39
 */

public class HeartbeatClient implements Runnable {

    private String serverIP = "127.0.0.1";
    private int serverPort = 8089;
    private String nodeID = UUID.randomUUID().toString();
    private boolean isRunning = true;
    //  最近的心跳时间
    private long lastHeartbeat;
    // 心跳间隔时间
    private long heartBeatInterval = 10 * 1000;

    @Override
    public void run() {
        try {
            while (isRunning) {
                HeartbeatHandler handler = RPCClient.getRemoteProxyObj(HeartbeatHandler.class, new InetSocketAddress(serverIP, serverPort));
                long startTime = System.currentTimeMillis();
                // 是否达到发送心跳的周期时间
                if (startTime - lastHeartbeat > heartBeatInterval) {
                    System.out.println("send a heart beat");
                    lastHeartbeat = startTime;

                    HeartbeatEntity entity = new HeartbeatEntity();
                    entity.setTime(startTime);
                    entity.setNodeID(nodeID);

                    // 向服务器发送心跳，并返回需要执行的命令
                    Cmder cmds = handler.sendHeartBeat(entity);

                    if (!processCommand(cmds))
                        continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean processCommand(Cmder cmds) {
        // ...
        return true;
    }

}
