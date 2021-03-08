package com.moqi.heartbeat;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author moqi
 * On 3/8/21 15:43
 */

public class HeartbeatLinstener {

    private final ConcurrentHashMap<String, Object> nodes = new ConcurrentHashMap<String, Object>();
    private final ConcurrentHashMap<String, Long> nodeStatus = new ConcurrentHashMap<String, Long>();
    private ExecutorService executor = Executors.newFixedThreadPool(20);
    private long timeout = 10 * 1000;

    // 服务器监听端口
    private int port = 8089;

    private HeartbeatLinstener() {
    }

    public static HeartbeatLinstener getInstance() {
        return SingleHolder.INSTANCE;
    }

    public ConcurrentHashMap<String, Object> getNodes() {
        return nodes;
    }

    public void registerNode(String nodeId, Object nodeInfo) {
        nodes.put(nodeId, nodeInfo);
        nodeStatus.put(nodeId, System.currentTimeMillis());
    }

    public void removeNode(String nodeID) {
        if (nodes.containsKey(nodeID)) {
            nodes.remove(nodeID);
        }
    }

    // 检测节点是否有效
    public boolean checkNodeValid(String key) {
        if (!nodes.containsKey(key) || !nodeStatus.containsKey(key)) return false;
        if ((System.currentTimeMillis() - nodeStatus.get(key)) > timeout) return false;
        return true;
    }

    // 删除所有失效节点
    public void removeInValidNode() {
        Iterator<Map.Entry<String, Long>> it = nodeStatus.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Long> e = it.next();
            if ((System.currentTimeMillis() - nodeStatus.get(e.getKey())) > timeout) {
                nodes.remove(e.getKey());
            }
        }
    }

    // 单例模式
    private static class SingleHolder {
        private static final HeartbeatLinstener INSTANCE = new HeartbeatLinstener();
    }

}