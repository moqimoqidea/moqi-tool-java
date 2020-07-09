package com.moqi.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * https://juejin.im/entry/5c84b4736fb9a049a42fdc31
 * https://www.cnblogs.com/rickiyang/p/11074159.html
 * https://www.jianshu.com/p/afe7b2dccee0
 *
 * @author moqi
 * On 7/9/20 21:24
 */

public class TTLCacheTest {

    private final static LoadingCache<Integer, String> CACHE = CacheBuilder.newBuilder()
            .initialCapacity(1)
            .maximumSize(1)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .removalListener(notification -> {
                System.out.println("key is " + notification.getKey() + ", and value is " + notification.getValue() + " 被移除,原因:" + notification.getCause());
            })
            .build(new TTLTestCacheLoader());

    /**
     * 580
     * key is 1, and value is 580 被移除,原因:EXPIRED
     * 307
     * key is 1, and value is 307 被移除,原因:SIZE
     * 3
     * key is 2, and value is 3 被移除,原因:SIZE
     * 10
     * key is 6, and value is 10 被移除,原因:REPLACED
     * 100
     * 100
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(CACHE.get(1, () -> String.valueOf(new Random().nextInt(1000))));
        Thread.sleep(2000L);
        System.out.println(CACHE.get(1, () -> String.valueOf(new Random().nextInt(1000))));
        System.out.println(CACHE.get(2));
        System.out.println(CACHE.get(6));

        CACHE.put(6, "100");
        System.out.println(CACHE.get(6));
        System.out.println(CACHE.get(6));
    }

    /**
     * 随机缓存加载,实际使用时应实现业务的缓存加载逻辑,例如从数据库获取数据
     */
    public static class TTLTestCacheLoader extends CacheLoader<Integer, String> {
        @Override
        public String load(Integer key) {
            return String.valueOf(key + new Random().nextInt(key));
        }
    }

}
