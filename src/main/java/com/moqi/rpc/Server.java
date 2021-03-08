package com.moqi.rpc;

import java.io.IOException;

/**
 * @author moqi
 * On 3/8/21 15:52
 */
public interface Server {

    void stop();

    void start() throws IOException;

    void register(Class serviceInterface, Class impl);

    boolean isRunning();

    int getPort();

}
