package com.moqi.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
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
