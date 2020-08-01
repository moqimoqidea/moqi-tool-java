package com.moqi.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 使用 JMH 进行基准性能测试
 * https://blog.csdn.net/cndmss/article/details/93771981
 *
 * JMH 使用第一个例子
 *
 * @author moqi
 * On 8/1/20 09:14
 */
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class JmhDemoOneTest {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(JmhDemoOneTest.class.getName())
                .build();
        new Runner(options).run();
    }

    /**
     * 测试sayHello的平局耗时
     */
    @Benchmark
    public void sayHello() throws Exception {
        //TODO 业务方法 ，此处用休眠的方式模拟业务耗时10 ms
        TimeUnit.MILLISECONDS.sleep(10);
    }

}
/*
# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=62096:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.moqi.test.jmh.JmhDemoOneTest.sayHello

# Run progress: 0.00% complete, ETA 00:08:20
# Fork: 1 of 5
# Warmup Iteration   1: 11.347 ms/op
# Warmup Iteration   2: 11.548 ms/op
# Warmup Iteration   3: 11.608 ms/op
# Warmup Iteration   4: 11.589 ms/op
# Warmup Iteration   5: 11.432 ms/op
Iteration   1: 11.434 ms/op
Iteration   2: 11.300 ms/op
Iteration   3: 11.565 ms/op
Iteration   4: 11.570 ms/op
Iteration   5: 11.582 ms/op

# Run progress: 20.00% complete, ETA 00:06:43
# Fork: 2 of 5
# Warmup Iteration   1: 11.352 ms/op
# Warmup Iteration   2: 11.560 ms/op
# Warmup Iteration   3: 11.415 ms/op
# Warmup Iteration   4: 11.537 ms/op
# Warmup Iteration   5: 11.667 ms/op
Iteration   1: 11.478 ms/op
Iteration   2: 11.549 ms/op
Iteration   3: 11.450 ms/op
Iteration   4: 11.388 ms/op
Iteration   5: 11.496 ms/op

# Run progress: 40.00% complete, ETA 00:05:02
# Fork: 3 of 5
# Warmup Iteration   1: 11.524 ms/op
# Warmup Iteration   2: 11.386 ms/op
# Warmup Iteration   3: 11.274 ms/op
# Warmup Iteration   4: 11.436 ms/op
# Warmup Iteration   5: 11.352 ms/op
Iteration   1: 11.444 ms/op
Iteration   2: 11.390 ms/op
Iteration   3: 11.631 ms/op
Iteration   4: 11.344 ms/op
Iteration   5: 11.402 ms/op

# Run progress: 60.00% complete, ETA 00:03:21
# Fork: 4 of 5
# Warmup Iteration   1: 11.650 ms/op
# Warmup Iteration   2: 11.533 ms/op
# Warmup Iteration   3: 11.525 ms/op
# Warmup Iteration   4: 11.595 ms/op
# Warmup Iteration   5: 11.474 ms/op
Iteration   1: 11.576 ms/op
Iteration   2: 11.651 ms/op
Iteration   3: 11.427 ms/op
Iteration   4: 11.267 ms/op
Iteration   5: 11.425 ms/op

# Run progress: 80.00% complete, ETA 00:01:40
# Fork: 5 of 5
# Warmup Iteration   1: 11.488 ms/op
# Warmup Iteration   2: 11.368 ms/op
# Warmup Iteration   3: 11.330 ms/op
# Warmup Iteration   4: 11.276 ms/op
# Warmup Iteration   5: 11.297 ms/op
Iteration   1: 11.265 ms/op
Iteration   2: 11.249 ms/op
Iteration   3: 11.208 ms/op
Iteration   4: 11.412 ms/op
Iteration   5: 11.516 ms/op


Result "com.moqi.test.jmh.JmhDemoOneTest.sayHello":
  11.441 ±(99.9%) 0.092 ms/op [Average]
  (min, avg, max) = (11.208, 11.441, 11.651), stdev = 0.123
  CI (99.9%): [11.349, 11.533] (assumes normal distribution)


# Run complete. Total time: 00:08:23

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                Mode  Cnt   Score   Error  Units
JmhDemoOneTest.sayHello  avgt   25  11.441 ± 0.092  ms/op

Process finished with exit code 0
*/
