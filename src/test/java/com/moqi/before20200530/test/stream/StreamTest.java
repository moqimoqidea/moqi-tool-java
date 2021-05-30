package com.moqi.before20200530.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Java 命令式方法和函数式方法在面对同一个问题的不同方案。
 *
 * 给定一个巨大的 Integer 的 List，计算其中大于数字 N 的 数量 M。
 * 可以看到 Stream 方式很容易并行，而普通方法可能需要借助线程模型来提高计算速度。
 * 广而化之这个问题，如果计算过程更复杂，意味着 Stream 的方式收益更高。
 *
 * @author moqi
 * On 8/13/20 17:22
 */

public class StreamTest {

    /**
     * 初始化数据开始
     * 初始化数据完毕
     * count = 99501251, foreachByIndex cost 235
     * count = 99501251, foreachByStream cost 417
     * count = 99501251, foreachByStreamParallel cost 119
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        System.out.println("初始化数据开始");

        for (int i = 0; i <= 100000000; i++) {
            list.add(random.nextInt(100000000));
        }

        System.out.println("初始化数据完毕");

        // 默认 Foreach
        foreachByIndex(list);
        // Stream
        foreachByStream(list);
        // Stream 并行
        foreachByStreamParallel(list);
    }

    private static void foreachByIndex(List<Integer> list) {
        long start = System.currentTimeMillis();
        int count = 0;

        for (Integer integer : list) {
            if (integer > 500000) {
                count += 1;
            }
        }

        System.out.println("count = " + count + ", foreachByIndex cost " + (System.currentTimeMillis() - start));
    }

    private static void foreachByStream(List<Integer> list) {
        long start = System.currentTimeMillis();

        int count = list.stream()
                .filter(x -> x > 500000)
                .mapToInt(x -> 1)
                .sum();

        System.out.println("count = " + count + ", foreachByStream cost " + (System.currentTimeMillis() - start));
    }

    private static void foreachByStreamParallel(List<Integer> list) {
        long start = System.currentTimeMillis();

        int count = list.stream()
                .parallel()
                .filter(x -> x > 500000)
                .mapToInt(x -> 1)
                .sum();

        System.out.println("count = " + count + ", foreachByStreamParallel cost " + (System.currentTimeMillis() - start));
    }

}
