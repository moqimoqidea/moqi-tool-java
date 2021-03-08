package com.moqi.rpc;

/**
 * @author moqi
 * On 3/8/21 15:52
 */

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi(String name) {
        return "Hi, " + name;
    }

}