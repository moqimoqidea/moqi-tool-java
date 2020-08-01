package com.moqi.test.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 测试验证字符串连接处理时，使用StringBuilder方式是否比直接相加好。
 *
 * @author moqi
 * On 8/1/20 09:29
 */

@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 3)
@State(Scope.Thread)
@Fork(1)
public class JmhDemoTwoTest {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(JmhDemoTwoTest.class.getName())
                .build();
        new Runner(options).run();
    }

    /**
     * 字符串个数
     */
    @Param({"10", "100", "1000"})
    private int number;

    /**
     * 字符串直接相加方式
     */
    @Benchmark
    public void StringAddMode() {
        String str = "";
        for (int i = 0; i < number; i++) {
            str = str + i;
        }
    }

    /**
     * 字符串通过StringBuilder的append方式
     */
    @Benchmark
    public void StringBuilderMode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number; i++) {
            sb.append(i);
        }
    }

}

/*
/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/bin/java -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=62401:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/lib/tools.jar:/Users/moqi/Code/moqi-tool-java/target/test-classes:/Users/moqi/Code/moqi-tool-java/target/classes:/Users/moqi/Applications/maven/repository/org/spockframework/spock-core/1.3-groovy-2.5/spock-core-1.3-groovy-2.5.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy/2.5.4/groovy-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-json/2.5.4/groovy-json-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-nio/2.5.4/groovy-nio-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-macro/2.5.4/groovy-macro-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-templates/2.5.4/groovy-templates-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-test/2.5.4/groovy-test-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-sql/2.5.4/groovy-sql-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-xml/2.5.4/groovy-xml-2.5.4.jar:/Users/moqi/Applications/maven/repository/junit/junit/4.12/junit-4.12.jar:/Users/moqi/Applications/maven/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/Users/moqi/Applications/maven/repository/org/junit/jupiter/junit-jupiter/5.5.1/junit-jupiter-5.5.1.jar:/Users/moqi/Applications/maven/repository/org/junit/jupiter/junit-jupiter-api/5.5.1/junit-jupiter-api-5.5.1.jar:/Users/moqi/Applications/maven/repository/org/apiguardian/apiguardian-api/1.1.0/apiguardian-api-1.1.0.jar:/Users/moqi/Applications/maven/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:/Users/moqi/Applications/maven/repository/org/junit/platform/junit-platform-commons/1.5.1/junit-platform-commons-1.5.1.jar:/Users/moqi/Applications/maven/repository/org/junit/jupiter/junit-jupiter-params/5.5.1/junit-jupiter-params-5.5.1.jar:/Users/moqi/Applications/maven/repository/org/junit/jupiter/junit-jupiter-engine/5.5.1/junit-jupiter-engine-5.5.1.jar:/Users/moqi/Applications/maven/repository/org/junit/platform/junit-platform-engine/1.5.1/junit-platform-engine-1.5.1.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-ant/2.5.7/groovy-ant-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/apache/ant/ant/1.9.13/ant-1.9.13.jar:/Users/moqi/Applications/maven/repository/org/apache/ant/ant-junit/1.9.13/ant-junit-1.9.13.jar:/Users/moqi/Applications/maven/repository/org/apache/ant/ant-launcher/1.9.13/ant-launcher-1.9.13.jar:/Users/moqi/Applications/maven/repository/org/apache/ant/ant-antlr/1.9.13/ant-antlr-1.9.13.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-cli-commons/2.5.7/groovy-cli-commons-2.5.7.jar:/Users/moqi/Applications/maven/repository/commons-cli/commons-cli/1.4/commons-cli-1.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-cli-picocli/2.5.7/groovy-cli-picocli-2.5.7.jar:/Users/moqi/Applications/maven/repository/info/picocli/picocli/3.9.5/picocli-3.9.5.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-console/2.5.7/groovy-console-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-datetime/2.5.7/groovy-datetime-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-docgenerator/2.5.7/groovy-docgenerator-2.5.7.jar:/Users/moqi/Applications/maven/repository/com/thoughtworks/qdox/qdox/1.12.1/qdox-1.12.1.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-groovydoc/2.5.7/groovy-groovydoc-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-groovysh/2.5.7/groovy-groovysh-2.5.7.jar:/Users/moqi/Applications/maven/repository/jline/jline/2.14.6/jline-2.14.6.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-jmx/2.5.7/groovy-jmx-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-jsr223/2.5.7/groovy-jsr223-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-servlet/2.5.7/groovy-servlet-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-swing/2.5.7/groovy-swing-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-test-junit5/2.5.7/groovy-test-junit5-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/junit/platform/junit-platform-launcher/1.4.0/junit-platform-launcher-1.4.0.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-testng/2.5.7/groovy-testng-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/testng/testng/6.13.1/testng-6.13.1.jar:/Users/moqi/Applications/maven/repository/com/beust/jcommander/1.72/jcommander-1.72.jar:/Users/moqi/Applications/maven/repository/cglib/cglib-nodep/3.3.0/cglib-nodep-3.3.0.jar:/Users/moqi/Applications/maven/repository/org/objenesis/objenesis/3.0.1/objenesis-3.0.1.jar:/Users/moqi/Applications/maven/repository/net/bytebuddy/byte-buddy/1.10.1/byte-buddy-1.10.1.jar:/Users/moqi/Applications/maven/repository/org/slf4j/slf4j-api/1.7.29/slf4j-api-1.7.29.jar:/Users/moqi/Applications/maven/repository/org/slf4j/slf4j-log4j12/1.7.29/slf4j-log4j12-1.7.29.jar:/Users/moqi/Applications/maven/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar:/Users/moqi/Applications/maven/repository/org/projectlombok/lombok/1.18.10/lombok-1.18.10.jar:/Users/moqi/Applications/maven/repository/org/apache/commons/commons-lang3/3.9/commons-lang3-3.9.jar:/Users/moqi/Applications/maven/repository/com/alibaba/fastjson/1.2.67/fastjson-1.2.67.jar:/Users/moqi/Applications/maven/repository/com/google/guava/guava/28.2-jre/guava-28.2-jre.jar:/Users/moqi/Applications/maven/repository/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:/Users/moqi/Applications/maven/repository/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/Users/moqi/Applications/maven/repository/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:/Users/moqi/Applications/maven/repository/org/checkerframework/checker-qual/2.10.0/checker-qual-2.10.0.jar:/Users/moqi/Applications/maven/repository/com/google/errorprone/error_prone_annotations/2.3.4/error_prone_annotations-2.3.4.jar:/Users/moqi/Applications/maven/repository/com/google/j2objc/j2objc-annotations/1.3/j2objc-annotations-1.3.jar:/Users/moqi/Applications/maven/repository/com/github/everit-org/json-schema/org.everit.json.schema/1.12.1/org.everit.json.schema-1.12.1.jar:/Users/moqi/Applications/maven/repository/org/json/json/20190722/json-20190722.jar:/Users/moqi/Applications/maven/repository/commons-validator/commons-validator/1.6/commons-validator-1.6.jar:/Users/moqi/Applications/maven/repository/commons-digester/commons-digester/1.8.1/commons-digester-1.8.1.jar:/Users/moqi/Applications/maven/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/Users/moqi/Applications/maven/repository/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar:/Users/moqi/Applications/maven/repository/com/damnhandy/handy-uri-templates/2.1.8/handy-uri-templates-2.1.8.jar:/Users/moqi/Applications/maven/repository/joda-time/joda-time/2.10.2/joda-time-2.10.2.jar:/Users/moqi/Applications/maven/repository/com/google/re2j/re2j/1.3/re2j-1.3.jar:/Users/moqi/Applications/maven/repository/org/openjdk/jmh/jmh-core/1.23/jmh-core-1.23.jar:/Users/moqi/Applications/maven/repository/net/sf/jopt-simple/jopt-simple/4.6/jopt-simple-4.6.jar:/Users/moqi/Applications/maven/repository/org/apache/commons/commons-math3/3.2/commons-math3-3.2.jar:/Users/moqi/Applications/maven/repository/org/openjdk/jmh/jmh-generator-annprocess/1.23/jmh-generator-annprocess-1.23.jar com.moqi.test.jmh.JmhDemoTwoTest
# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=62401:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 1 iterations, 1 ms each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.moqi.test.jmh.JmhDemoTwoTest.StringAddMode
# Parameters: (number = 10)

# Run progress: 0.00% complete, ETA 00:01:30
# Fork: 1 of 1
# Warmup Iteration   1: 466096.941 ops/s
Iteration   1: 7218785.943 ops/s
Iteration   2: 9230006.599 ops/s
Iteration   3: 8174431.390 ops/s
Iteration   4: 8171184.239 ops/s
Iteration   5: 8178129.148 ops/s


Result "com.moqi.test.jmh.JmhDemoTwoTest.StringAddMode":
  8194507.464 ±(99.9%) 2740123.615 ops/s [Average]
  (min, avg, max) = (7218785.943, 8194507.464, 9230006.599), stdev = 711601.401
  CI (99.9%): [5454383.848, 10934631.079] (assumes normal distribution)


# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=62401:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 1 iterations, 1 ms each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.moqi.test.jmh.JmhDemoTwoTest.StringAddMode
# Parameters: (number = 100)

# Run progress: 16.67% complete, ETA 00:01:19
# Fork: 1 of 1
# Warmup Iteration   1: 11939.945 ops/s
Iteration   1: 362663.195 ops/s
Iteration   2: 494355.181 ops/s
Iteration   3: 490701.782 ops/s
Iteration   4: 486537.675 ops/s
Iteration   5: 482300.500 ops/s


Result "com.moqi.test.jmh.JmhDemoTwoTest.StringAddMode":
  463311.666 ±(99.9%) 217348.780 ops/s [Average]
  (min, avg, max) = (362663.195, 463311.666, 494355.181), stdev = 56444.788
  CI (99.9%): [245962.886, 680660.447] (assumes normal distribution)


# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=62401:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 1 iterations, 1 ms each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.moqi.test.jmh.JmhDemoTwoTest.StringAddMode
# Parameters: (number = 1000)

# Run progress: 33.33% complete, ETA 00:01:03
# Fork: 1 of 1
# Warmup Iteration   1: 156.595 ops/s
Iteration   1: 3008.504 ops/s
Iteration   2: 4433.883 ops/s
Iteration   3: 4483.862 ops/s
Iteration   4: 4457.009 ops/s
Iteration   5: 4559.752 ops/s


Result "com.moqi.test.jmh.JmhDemoTwoTest.StringAddMode":
  4188.602 ±(99.9%) 2546.791 ops/s [Average]
  (min, avg, max) = (3008.504, 4188.602, 4559.752), stdev = 661.394
  CI (99.9%): [1641.810, 6735.393] (assumes normal distribution)


# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=62401:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 1 iterations, 1 ms each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.moqi.test.jmh.JmhDemoTwoTest.StringBuilderMode
# Parameters: (number = 10)

# Run progress: 50.00% complete, ETA 00:00:47
# Fork: 1 of 1
# Warmup Iteration   1: 1083631.384 ops/s
Iteration   1: 17188807.357 ops/s
Iteration   2: 17691893.991 ops/s
Iteration   3: 17537889.820 ops/s
Iteration   4: 17700590.762 ops/s
Iteration   5: 17591097.505 ops/s


Result "com.moqi.test.jmh.JmhDemoTwoTest.StringBuilderMode":
  17542055.887 ±(99.9%) 804940.537 ops/s [Average]
  (min, avg, max) = (17188807.357, 17542055.887, 17700590.762), stdev = 209040.501
  CI (99.9%): [16737115.351, 18346996.424] (assumes normal distribution)


# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=62401:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 1 iterations, 1 ms each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.moqi.test.jmh.JmhDemoTwoTest.StringBuilderMode
# Parameters: (number = 100)

# Run progress: 66.67% complete, ETA 00:00:31
# Fork: 1 of 1
# Warmup Iteration   1: 72451.813 ops/s
Iteration   1: 1131889.280 ops/s
Iteration   2: 1169412.714 ops/s
Iteration   3: 1170124.765 ops/s
Iteration   4: 1158407.263 ops/s
Iteration   5: 1105767.243 ops/s


Result "com.moqi.test.jmh.JmhDemoTwoTest.StringBuilderMode":
  1147120.253 ±(99.9%) 107121.494 ops/s [Average]
  (min, avg, max) = (1105767.243, 1147120.253, 1170124.765), stdev = 27819.112
  CI (99.9%): [1039998.758, 1254241.747] (assumes normal distribution)


# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=62401:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 1 iterations, 1 ms each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.moqi.test.jmh.JmhDemoTwoTest.StringBuilderMode
# Parameters: (number = 1000)

# Run progress: 83.33% complete, ETA 00:00:15
# Fork: 1 of 1
# Warmup Iteration   1: 8044.074 ops/s
Iteration   1: 86232.915 ops/s
Iteration   2: 90139.863 ops/s
Iteration   3: 89455.494 ops/s
Iteration   4: 90758.765 ops/s
Iteration   5: 89884.748 ops/s


Result "com.moqi.test.jmh.JmhDemoTwoTest.StringBuilderMode":
  89294.357 ±(99.9%) 6835.931 ops/s [Average]
  (min, avg, max) = (86232.915, 89294.357, 90758.765), stdev = 1775.270
  CI (99.9%): [82458.426, 96130.288] (assumes normal distribution)


# Run complete. Total time: 00:01:34

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                         (number)   Mode  Cnt         Score         Error  Units
JmhDemoTwoTest.StringAddMode            10  thrpt    5   8194507.464 ± 2740123.615  ops/s
JmhDemoTwoTest.StringAddMode           100  thrpt    5    463311.666 ±  217348.780  ops/s
JmhDemoTwoTest.StringAddMode          1000  thrpt    5      4188.602 ±    2546.791  ops/s
JmhDemoTwoTest.StringBuilderMode        10  thrpt    5  17542055.887 ±  804940.537  ops/s
JmhDemoTwoTest.StringBuilderMode       100  thrpt    5   1147120.253 ±  107121.494  ops/s
JmhDemoTwoTest.StringBuilderMode      1000  thrpt    5     89294.357 ±    6835.931  ops/s

Process finished with exit code 0

从测试结果可以看出，在字符串连接数量分别为10、100、1000时，通过StringBuilder处理字符串的方式比直接相加的方式性能都要强一些；
如，当字符窜数量为1000时，直接相加方式的方法吞吐量为 4188.602 ops/s，StringBuilder的方式方法吞吐量达到 89294.357 ops/s 。
（当然具体测试结果值和机器配置、JVM配置有关）
*/
