package com.moqi.in20210718

import spock.lang.Specification

/**
 * 练习 Java 8 Map 新增 API
 *
 * @author moqi* On 2021/7/18 15:46
 */
class Java8MapTest extends Specification {

    def "getOrDefault should work"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        expect:
        map.getOrDefault("Tom", 100) == 3
        map.getOrDefault("TomNew", 100) == 100
    }

    def "foreach should println key and value"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]
        List<Integer> list = []

        when:
        map.forEach((k, v) -> list.add(v))

        then:
        list == [3, 5]
    }

    def "compute should work"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        when:
        map.compute("Tom", (k, v) -> v + 1)

        then:
        map.get("Tom") == 4
    }

    def "computeIfPresent should work"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        when:
        map.computeIfPresent("Nancy", (k, v) -> v + 1)

        then:
        map.size() == 2
    }

    def "computeIfAbsent should work"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        when:
        map.computeIfAbsent("Nancy", x -> x.length() * 1000)

        then:
        map.get("Nancy") == 5000
    }

    def "putIfAbsent should work"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        expect:
        map.putIfAbsent("Nancy", 10) == null
        map.putIfAbsent("Nancy", 100) == 10
        map.putIfAbsent("Nancy", 1000) == 10
        map["Nancy"] == 10
    }

    // https://www.geeksforgeeks.org/hashmap-mergekey-value-bifunction-method-in-java-with-examples/
    def "merge should work fine"() {
        given:
        def words = ["Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz"]
        Map<String, Integer> map = [:]

        when:
        words.forEach(word -> map.merge(
                word, 1, (prev, one) -> prev + one))

        then:
        map == ["Foo": 3, "Bar": 1, "Buzz": 2, "Fizz": 2]
    }

    // https://nurkiewicz.com/2019/03/mapmerge-one-method-to-rule-them-all.html
    def "merge should work fine 2, not a good demo"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        when:
        ["Tom": 1000].forEach((k, v) ->
                map.merge(k, v, (v1, v2) -> v1 > v2 ? v1 : v2)
        )

        then:
        map.get("Tom") == 1000
    }

    def "remove key and value should work"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        when:
        // remove key and value not present, do nothing
        map.remove("Tom", 10)

        then:
        map["Tom"] == 3

        when:
        map.remove("Tom", 3)

        then:
        map["Tom"] == null
    }

    def "replace should work"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        when:
        map.replace("Tom", 10)

        then:
        map["Tom"] == 10

        when:
        // replace key and old value not present, do nothing
        map.replace("Tom", 1000, 1)

        then:
        map["Tom"] == 10

        when:
        map.replace("Tom", 10, 1)

        then:
        map["Tom"] == 1
    }

    def "replaceAll should work"() {
        given:
        Map<String, Integer> map = ["Tom": 3, "Jerry": 5]

        when:
        map.replaceAll((k, v) -> v + 1000)

        then:
        map == ["Tom": 1003, "Jerry": 1005]
    }

}
