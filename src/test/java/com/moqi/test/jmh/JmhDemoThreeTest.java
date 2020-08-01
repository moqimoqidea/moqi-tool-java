package com.moqi.test.jmh;

import com.moqi.bean.Userinfo;
import com.moqi.json.JsonUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试常用序列化json库fastJson、gson、jackson的性能（均为截止 2020.08 最新版本）。
 *
 * @author moqi
 * On 8/1/20 09:38
 */

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.SingleShotTime)
@Warmup(iterations = 5)
@Measurement(iterations = 1)
@State(Scope.Benchmark)
@Fork(1)
public class JmhDemoThreeTest {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(JmhDemoThreeTest.class.getName())
                .build();
        new Runner(options).run();
    }

    /**
     * 序列化次数
     */
    @Param({"100", "10000", "1000000"})
    private int number;
    private Userinfo userinfo;
    private String fastjson_jsonStr;
    private String gson_jsonStr;
    private String jackson_jsonStr;

    /**
     * fastjson bean2Json
     */
    @Benchmark
    public void fastjson_bean2Json() {
        for (int i = 0; i < number; i++) {
            JsonUtil.fastjson_bean2Json(userinfo);
        }
    }

    /**
     * gson bean2Json
     */
    @Benchmark
    public void gson_bean2Json() {
        for (int i = 0; i < number; i++) {
            JsonUtil.gson_bean2Json(userinfo);
        }
    }

    /**
     * jackson bean2Json
     */
    @Benchmark
    public void jackson_bean2Json() {
        for (int i = 0; i < number; i++) {
            JsonUtil.jackson_bean2Json(userinfo);
        }
    }

    /**
     * fastjson json2Bean
     */
    @Benchmark
    public void fastjson_json2Bean() {
        for (int i = 0; i < number; i++) {
            JsonUtil.fastjson_json2Bean(fastjson_jsonStr, Userinfo.class);
        }
    }

    /**
     * gson json2Bean
     */
    @Benchmark
    public void gson_json2Bean() {
        for (int i = 0; i < number; i++) {
            JsonUtil.gson_json2Bean(gson_jsonStr, Userinfo.class);
        }
    }

    /**
     * jackson json2Bean
     */
    @Benchmark
    public void jackson_json2Bean() {
        for (int i = 0; i < number; i++) {
            JsonUtil.jackson_json2Bean(jackson_jsonStr, Userinfo.class);
        }
    }

    /**
     * 初始化参数
     */
    @Setup
    public void init() {
        userinfo = new Userinfo();
        userinfo.setUsername("张三");
        userinfo.setGender("男");
        userinfo.setAge(18);
        userinfo.setBirthday(new Date());
        userinfo.setCreateTime(System.currentTimeMillis());
        List<String> list = new ArrayList<>();
        list.add("北京三里屯儿那条街那条巷那一号");
        list.add("上海三里屯儿那条街那条巷那一号");
        list.add("深圳三里屯儿那条街那条巷那一号");
        userinfo.setAddress(list);

        fastjson_jsonStr = JsonUtil.fastjson_bean2Json(userinfo);
        gson_jsonStr = JsonUtil.gson_bean2Json(userinfo);
        jackson_jsonStr = JsonUtil.jackson_bean2Json(userinfo);
    }

}

/*
/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/bin/java -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/lib/tools.jar:/Users/moqi/Code/moqi-tool-java/target/test-classes:/Users/moqi/Code/moqi-tool-java/target/classes:/Users/moqi/Applications/maven/repository/org/spockframework/spock-core/1.3-groovy-2.5/spock-core-1.3-groovy-2.5.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy/2.5.4/groovy-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-json/2.5.4/groovy-json-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-nio/2.5.4/groovy-nio-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-macro/2.5.4/groovy-macro-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-templates/2.5.4/groovy-templates-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-test/2.5.4/groovy-test-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-sql/2.5.4/groovy-sql-2.5.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-xml/2.5.4/groovy-xml-2.5.4.jar:/Users/moqi/Applications/maven/repository/junit/junit/4.12/junit-4.12.jar:/Users/moqi/Applications/maven/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/Users/moqi/Applications/maven/repository/org/junit/jupiter/junit-jupiter/5.5.1/junit-jupiter-5.5.1.jar:/Users/moqi/Applications/maven/repository/org/junit/jupiter/junit-jupiter-api/5.5.1/junit-jupiter-api-5.5.1.jar:/Users/moqi/Applications/maven/repository/org/apiguardian/apiguardian-api/1.1.0/apiguardian-api-1.1.0.jar:/Users/moqi/Applications/maven/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:/Users/moqi/Applications/maven/repository/org/junit/platform/junit-platform-commons/1.5.1/junit-platform-commons-1.5.1.jar:/Users/moqi/Applications/maven/repository/org/junit/jupiter/junit-jupiter-params/5.5.1/junit-jupiter-params-5.5.1.jar:/Users/moqi/Applications/maven/repository/org/junit/jupiter/junit-jupiter-engine/5.5.1/junit-jupiter-engine-5.5.1.jar:/Users/moqi/Applications/maven/repository/org/junit/platform/junit-platform-engine/1.5.1/junit-platform-engine-1.5.1.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-ant/2.5.7/groovy-ant-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/apache/ant/ant/1.9.13/ant-1.9.13.jar:/Users/moqi/Applications/maven/repository/org/apache/ant/ant-junit/1.9.13/ant-junit-1.9.13.jar:/Users/moqi/Applications/maven/repository/org/apache/ant/ant-launcher/1.9.13/ant-launcher-1.9.13.jar:/Users/moqi/Applications/maven/repository/org/apache/ant/ant-antlr/1.9.13/ant-antlr-1.9.13.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-cli-commons/2.5.7/groovy-cli-commons-2.5.7.jar:/Users/moqi/Applications/maven/repository/commons-cli/commons-cli/1.4/commons-cli-1.4.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-cli-picocli/2.5.7/groovy-cli-picocli-2.5.7.jar:/Users/moqi/Applications/maven/repository/info/picocli/picocli/3.9.5/picocli-3.9.5.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-console/2.5.7/groovy-console-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-datetime/2.5.7/groovy-datetime-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-docgenerator/2.5.7/groovy-docgenerator-2.5.7.jar:/Users/moqi/Applications/maven/repository/com/thoughtworks/qdox/qdox/1.12.1/qdox-1.12.1.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-groovydoc/2.5.7/groovy-groovydoc-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-groovysh/2.5.7/groovy-groovysh-2.5.7.jar:/Users/moqi/Applications/maven/repository/jline/jline/2.14.6/jline-2.14.6.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-jmx/2.5.7/groovy-jmx-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-jsr223/2.5.7/groovy-jsr223-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-servlet/2.5.7/groovy-servlet-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-swing/2.5.7/groovy-swing-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-test-junit5/2.5.7/groovy-test-junit5-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/junit/platform/junit-platform-launcher/1.4.0/junit-platform-launcher-1.4.0.jar:/Users/moqi/Applications/maven/repository/org/codehaus/groovy/groovy-testng/2.5.7/groovy-testng-2.5.7.jar:/Users/moqi/Applications/maven/repository/org/testng/testng/6.13.1/testng-6.13.1.jar:/Users/moqi/Applications/maven/repository/com/beust/jcommander/1.72/jcommander-1.72.jar:/Users/moqi/Applications/maven/repository/cglib/cglib-nodep/3.3.0/cglib-nodep-3.3.0.jar:/Users/moqi/Applications/maven/repository/org/objenesis/objenesis/3.0.1/objenesis-3.0.1.jar:/Users/moqi/Applications/maven/repository/net/bytebuddy/byte-buddy/1.10.1/byte-buddy-1.10.1.jar:/Users/moqi/Applications/maven/repository/org/slf4j/slf4j-api/1.7.29/slf4j-api-1.7.29.jar:/Users/moqi/Applications/maven/repository/org/slf4j/slf4j-log4j12/1.7.29/slf4j-log4j12-1.7.29.jar:/Users/moqi/Applications/maven/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar:/Users/moqi/Applications/maven/repository/org/projectlombok/lombok/1.18.10/lombok-1.18.10.jar:/Users/moqi/Applications/maven/repository/org/apache/commons/commons-lang3/3.9/commons-lang3-3.9.jar:/Users/moqi/Applications/maven/repository/com/alibaba/fastjson/1.2.67/fastjson-1.2.67.jar:/Users/moqi/Applications/maven/repository/com/google/guava/guava/28.2-jre/guava-28.2-jre.jar:/Users/moqi/Applications/maven/repository/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:/Users/moqi/Applications/maven/repository/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/Users/moqi/Applications/maven/repository/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:/Users/moqi/Applications/maven/repository/org/checkerframework/checker-qual/2.10.0/checker-qual-2.10.0.jar:/Users/moqi/Applications/maven/repository/com/google/errorprone/error_prone_annotations/2.3.4/error_prone_annotations-2.3.4.jar:/Users/moqi/Applications/maven/repository/com/google/j2objc/j2objc-annotations/1.3/j2objc-annotations-1.3.jar:/Users/moqi/Applications/maven/repository/com/github/everit-org/json-schema/org.everit.json.schema/1.12.1/org.everit.json.schema-1.12.1.jar:/Users/moqi/Applications/maven/repository/org/json/json/20190722/json-20190722.jar:/Users/moqi/Applications/maven/repository/commons-validator/commons-validator/1.6/commons-validator-1.6.jar:/Users/moqi/Applications/maven/repository/commons-digester/commons-digester/1.8.1/commons-digester-1.8.1.jar:/Users/moqi/Applications/maven/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/Users/moqi/Applications/maven/repository/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar:/Users/moqi/Applications/maven/repository/com/damnhandy/handy-uri-templates/2.1.8/handy-uri-templates-2.1.8.jar:/Users/moqi/Applications/maven/repository/joda-time/joda-time/2.10.2/joda-time-2.10.2.jar:/Users/moqi/Applications/maven/repository/com/google/re2j/re2j/1.3/re2j-1.3.jar:/Users/moqi/Applications/maven/repository/org/openjdk/jmh/jmh-core/1.23/jmh-core-1.23.jar:/Users/moqi/Applications/maven/repository/net/sf/jopt-simple/jopt-simple/4.6/jopt-simple-4.6.jar:/Users/moqi/Applications/maven/repository/org/apache/commons/commons-math3/3.2/commons-math3-3.2.jar:/Users/moqi/Applications/maven/repository/org/openjdk/jmh/jmh-generator-annprocess/1.23/jmh-generator-annprocess-1.23.jar:/Users/moqi/Applications/maven/repository/com/google/code/gson/gson/2.8.6/gson-2.8.6.jar:/Users/moqi/Applications/maven/repository/com/fasterxml/jackson/core/jackson-databind/2.11.1/jackson-databind-2.11.1.jar:/Users/moqi/Applications/maven/repository/com/fasterxml/jackson/core/jackson-core/2.11.1/jackson-core-2.11.1.jar:/Users/moqi/Applications/maven/repository/com/fasterxml/jackson/core/jackson-annotations/2.11.1/jackson-annotations-2.11.1.jar com.moqi.test.jmh.JmhDemoThreeTest
# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.fastjson_bean2Json
# Parameters: (number = 100)

# Run progress: 0.00% complete, ETA 00:00:00
# Fork: 1 of 1
# Warmup Iteration   1: 3.017 ms/op
# Warmup Iteration   2: 3.171 ms/op
# Warmup Iteration   3: 3.536 ms/op
# Warmup Iteration   4: 3.535 ms/op
# Warmup Iteration   5: 3.331 ms/op
Iteration   1: 3.610 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.fastjson_bean2Json
# Parameters: (number = 10000)

# Run progress: 5.56% complete, ETA 00:00:21
# Fork: 1 of 1
# Warmup Iteration   1: 46.984 ms/op
# Warmup Iteration   2: 10.566 ms/op
# Warmup Iteration   3: 10.920 ms/op
# Warmup Iteration   4: 14.115 ms/op
# Warmup Iteration   5: 7.938 ms/op
Iteration   1: 12.978 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.fastjson_bean2Json
# Parameters: (number = 1000000)

# Run progress: 11.11% complete, ETA 00:00:19
# Fork: 1 of 1
# Warmup Iteration   1: 411.870 ms/op
# Warmup Iteration   2: 393.176 ms/op
# Warmup Iteration   3: 328.852 ms/op
# Warmup Iteration   4: 457.082 ms/op
# Warmup Iteration   5: 318.536 ms/op
Iteration   1: 309.552 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.fastjson_json2Bean
# Parameters: (number = 100)

# Run progress: 16.67% complete, ETA 00:00:28
# Fork: 1 of 1
# Warmup Iteration   1: 11.165 ms/op
# Warmup Iteration   2: 1.339 ms/op
# Warmup Iteration   3: 1.145 ms/op
# Warmup Iteration   4: 1.101 ms/op
# Warmup Iteration   5: 0.977 ms/op
Iteration   1: 1.146 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.fastjson_json2Bean
# Parameters: (number = 10000)

# Run progress: 22.22% complete, ETA 00:00:23
# Fork: 1 of 1
# Warmup Iteration   1: 39.560 ms/op
# Warmup Iteration   2: 11.565 ms/op
# Warmup Iteration   3: 10.215 ms/op
# Warmup Iteration   4: 7.884 ms/op
# Warmup Iteration   5: 7.109 ms/op
Iteration   1: 7.054 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.fastjson_json2Bean
# Parameters: (number = 1000000)

# Run progress: 27.78% complete, ETA 00:00:20
# Fork: 1 of 1
# Warmup Iteration   1: 463.709 ms/op
# Warmup Iteration   2: 410.475 ms/op
# Warmup Iteration   3: 357.388 ms/op
# Warmup Iteration   4: 448.245 ms/op
# Warmup Iteration   5: 403.204 ms/op
Iteration   1: 357.861 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.gson_bean2Json
# Parameters: (number = 100)

# Run progress: 33.33% complete, ETA 00:00:22
# Fork: 1 of 1
# Warmup Iteration   1: 6.661 ms/op
# Warmup Iteration   2: 3.481 ms/op
# Warmup Iteration   3: 2.596 ms/op
# Warmup Iteration   4: 2.184 ms/op
# Warmup Iteration   5: 1.847 ms/op
Iteration   1: 1.627 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.gson_bean2Json
# Parameters: (number = 10000)

# Run progress: 38.89% complete, ETA 00:00:19
# Fork: 1 of 1
# Warmup Iteration   1: 76.588 ms/op
# Warmup Iteration   2: 50.829 ms/op
# Warmup Iteration   3: 27.249 ms/op
# Warmup Iteration   4: 22.888 ms/op
# Warmup Iteration   5: 23.127 ms/op
Iteration   1: 22.601 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.gson_bean2Json
# Parameters: (number = 1000000)

# Run progress: 44.44% complete, ETA 00:00:16
# Fork: 1 of 1
# Warmup Iteration   1: 2284.750 ms/op
# Warmup Iteration   2: 1786.460 ms/op
# Warmup Iteration   3: 1313.005 ms/op
# Warmup Iteration   4: 1332.993 ms/op
# Warmup Iteration   5: 1355.206 ms/op
Iteration   1: 1314.324 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.gson_json2Bean
# Parameters: (number = 100)

# Run progress: 50.00% complete, ETA 00:00:23
# Fork: 1 of 1
# Warmup Iteration   1: 22.569 ms/op
# Warmup Iteration   2: 13.598 ms/op
# Warmup Iteration   3: 10.315 ms/op
# Warmup Iteration   4: 6.354 ms/op
# Warmup Iteration   5: 5.435 ms/op
Iteration   1: 4.908 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.gson_json2Bean
# Parameters: (number = 10000)

# Run progress: 55.56% complete, ETA 00:00:20
# Fork: 1 of 1
# Warmup Iteration   1: 421.554 ms/op
# Warmup Iteration   2: 141.316 ms/op
# Warmup Iteration   3: 168.583 ms/op
# Warmup Iteration   4: 133.394 ms/op
# Warmup Iteration   5: 112.753 ms/op
Iteration   1: 229.011 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.gson_json2Bean
# Parameters: (number = 1000000)

# Run progress: 61.11% complete, ETA 00:00:17
# Fork: 1 of 1
# Warmup Iteration   1: 7803.712 ms/op
# Warmup Iteration   2: 6259.574 ms/op
# Warmup Iteration   3: 6248.508 ms/op
# Warmup Iteration   4: 6168.465 ms/op
# Warmup Iteration   5: 6479.493 ms/op
Iteration   1: 6794.500 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.jackson_bean2Json
# Parameters: (number = 100)

# Run progress: 66.67% complete, ETA 00:00:34
# Fork: 1 of 1
# Warmup Iteration   1: 3.457 ms/op
# Warmup Iteration   2: 2.337 ms/op
# Warmup Iteration   3: 2.056 ms/op
# Warmup Iteration   4: 1.509 ms/op
# Warmup Iteration   5: 1.334 ms/op
Iteration   1: 1.380 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.jackson_bean2Json
# Parameters: (number = 10000)

# Run progress: 72.22% complete, ETA 00:00:26
# Fork: 1 of 1
# Warmup Iteration   1: 35.762 ms/op
# Warmup Iteration   2: 11.764 ms/op
# Warmup Iteration   3: 11.037 ms/op
# Warmup Iteration   4: 10.051 ms/op
# Warmup Iteration   5: 9.045 ms/op
Iteration   1: 8.735 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.jackson_bean2Json
# Parameters: (number = 1000000)

# Run progress: 77.78% complete, ETA 00:00:20
# Fork: 1 of 1
# Warmup Iteration   1: 482.225 ms/op
# Warmup Iteration   2: 435.442 ms/op
# Warmup Iteration   3: 353.272 ms/op
# Warmup Iteration   4: 524.133 ms/op
# Warmup Iteration   5: 357.080 ms/op
Iteration   1: 350.476 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.jackson_json2Bean
# Parameters: (number = 100)

# Run progress: 83.33% complete, ETA 00:00:14
# Fork: 1 of 1
# Warmup Iteration   1: 30.519 ms/op
# Warmup Iteration   2: 3.068 ms/op
# Warmup Iteration   3: 2.508 ms/op
# Warmup Iteration   4: 2.103 ms/op
# Warmup Iteration   5: 1.689 ms/op
Iteration   1: 2.105 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.jackson_json2Bean
# Parameters: (number = 10000)

# Run progress: 88.89% complete, ETA 00:00:09
# Fork: 1 of 1
# Warmup Iteration   1: 77.097 ms/op
# Warmup Iteration   2: 18.901 ms/op
# Warmup Iteration   3: 15.153 ms/op
# Warmup Iteration   4: 16.645 ms/op
# Warmup Iteration   5: 8.388 ms/op
Iteration   1: 7.396 ms/op



# JMH version: 1.23
# VM version: JDK 1.8.0_242, OpenJDK 64-Bit Server VM, 25.242-b08
# VM invoker: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/lib/idea_rt.jar=63724:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/202.6397.94/IntelliJ IDEA 2020.2 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, single-shot each
# Measurement: 1 iterations, single-shot each
# Timeout: 10 min per iteration
# Threads: 1 thread
# Benchmark mode: Single shot invocation time
# Benchmark: com.moqi.test.jmh.JmhDemoThreeTest.jackson_json2Bean
# Parameters: (number = 1000000)

# Run progress: 94.44% complete, ETA 00:00:04
# Fork: 1 of 1
# Warmup Iteration   1: 753.684 ms/op
# Warmup Iteration   2: 703.041 ms/op
# Warmup Iteration   3: 786.620 ms/op
# Warmup Iteration   4: 624.202 ms/op
# Warmup Iteration   5: 588.559 ms/op
Iteration   1: 584.307 ms/op



# Run complete. Total time: 00:01:21

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                            (number)  Mode  Cnt     Score   Error  Units
JmhDemoThreeTest.fastjson_bean2Json       100    ss          3.610          ms/op
JmhDemoThreeTest.fastjson_bean2Json     10000    ss         12.978          ms/op
JmhDemoThreeTest.fastjson_bean2Json   1000000    ss        309.552          ms/op
JmhDemoThreeTest.fastjson_json2Bean       100    ss          1.146          ms/op
JmhDemoThreeTest.fastjson_json2Bean     10000    ss          7.054          ms/op
JmhDemoThreeTest.fastjson_json2Bean   1000000    ss        357.861          ms/op
JmhDemoThreeTest.gson_bean2Json           100    ss          1.627          ms/op
JmhDemoThreeTest.gson_bean2Json         10000    ss         22.601          ms/op
JmhDemoThreeTest.gson_bean2Json       1000000    ss       1314.324          ms/op
JmhDemoThreeTest.gson_json2Bean           100    ss          4.908          ms/op
JmhDemoThreeTest.gson_json2Bean         10000    ss        229.011          ms/op
JmhDemoThreeTest.gson_json2Bean       1000000    ss       6794.500          ms/op
JmhDemoThreeTest.jackson_bean2Json        100    ss          1.380          ms/op
JmhDemoThreeTest.jackson_bean2Json      10000    ss          8.735          ms/op
JmhDemoThreeTest.jackson_bean2Json    1000000    ss        350.476          ms/op
JmhDemoThreeTest.jackson_json2Bean        100    ss          2.105          ms/op
JmhDemoThreeTest.jackson_json2Bean      10000    ss          7.396          ms/op
JmhDemoThreeTest.jackson_json2Bean    1000000    ss        584.307          ms/op

Process finished with exit code 0
*/
