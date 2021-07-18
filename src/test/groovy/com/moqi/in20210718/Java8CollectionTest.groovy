package com.moqi.in20210718

import spock.lang.Specification

import java.util.stream.Collectors

/**
 * 练习 Java 8 Collection 新增 API
 *
 * @author moqi* On 2021/7/18 15:46
 */
class Java8CollectionTest extends Specification {

    def "removeIf should work"() {
        given:
        List<String> list = ["orange", "eight", "apple"]

        when:
        list.removeIf(x -> x.startsWith("e"))

        then:
        list.size() == 2
        list == ["orange", "apple"]
    }

    def "parallelStream should work"() {
        given:
        List<String> list = ["orange", "eight", "apple"]

        when:
        List<Integer> integerList = list.parallelStream()
                .map(String::length)
                .collect(Collectors.toList())

        then:
        integerList == [6, 5, 5]
    }

    def "sort should work"() {
        given:
        List<String> list = ["orange", "eight", "apple"]

        when:
        list.sort(Comparator.comparingInt(String::length))

        then:
        list == ["eight", "apple", "orange"]

        when:
        list.sort(Comparator.comparingInt(String::length).reversed())

        then:
        list == ["orange", "eight", "apple"]

        when:
        list.sort((x, y) -> x.substring(0, 1).compareTo(y.substring(0, 1)))

        then:
        list == ["apple", "eight", "orange"]
    }

}
