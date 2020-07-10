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
            // cache 的初始容量设定为 1
            .initialCapacity(1)
            // cache 的最大容量设定为 1
            .maximumSize(1)
            // cache 中的 k, v 设定 1 秒后过期
            .expireAfterWrite(1, TimeUnit.SECONDS)
            // 当数据被删除时打印出 k, v 和删除原因
            .removalListener(notification -> {
                // 被移除原因主要有三种：REPLACED 替换、SIZE 容量限制、EXPIRED 过期
                System.out.println("key is " + notification.getKey() + ", and value is " + notification.getValue() + " 被移除,原因:" + notification.getCause());
            })
            // 当 get key 在 cache 中为 null 时，使用 cache loader 生成对应的 value
            .build(new TTLTestCacheLoader());

    /**
     * 7
     * key is 1, and value is 7 被移除,原因:EXPIRED
     * 6027
     * key is 1, and value is 6027 被移除,原因:SIZE
     * 4
     * key is 2, and value is 4 被移除,原因:SIZE
     * 36
     * key is 6, and value is 36 被移除,原因:REPLACED
     * 100
     * 100
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // get 方法带一个 Callable 的参数，若 key 存在则取值 value，不存在则使用 Callable 生成
        System.out.println(CACHE.get(1, () -> String.valueOf(new Random().nextInt(10))));
        Thread.sleep(2000L);
        System.out.println(CACHE.get(1, () -> String.valueOf(new Random().nextInt(10000))));
        // get 方法不带其他参数，若 key 存在则取值 value，不存在则使用 cache build 指定的 CacheLoader 生成
        System.out.println(CACHE.get(2));
        System.out.println(CACHE.get(6));

        // 直接的 set 方法
        CACHE.put(6, "100");
        System.out.println(CACHE.get(6));
        System.out.println(CACHE.get(6));
    }

    /**
     * 随机缓存加载，实际使用时应实现业务的缓存加载逻辑，例如从数据库获取数据
     * 本测试中生成 数字平方 的字符串格式
     */
    public static class TTLTestCacheLoader extends CacheLoader<Integer, String> {
        @Override
        public String load(Integer key) {
            return String.valueOf(key * key);
        }
    }

}
