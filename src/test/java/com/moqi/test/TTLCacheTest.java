package com.moqi.test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.time.Duration;

/**
 * @author moqi
 * On 7/9/20 21:24
 */

public class TTLCacheTest {

    public static void main(String[] args) throws InterruptedException {
        Cache<Object, Object> build = CacheBuilder
                .newBuilder()
                .maximumSize(10)
                .expireAfterWrite(Duration.ofSeconds(1L))
                .build();

        build.put("1", "1");
        System.out.println("build = " + build.size());
        Thread.sleep(1000L);
        build.put("2", "2");
        build.put("3", "3");
        System.out.println("build = " + build.size());

    }

}
