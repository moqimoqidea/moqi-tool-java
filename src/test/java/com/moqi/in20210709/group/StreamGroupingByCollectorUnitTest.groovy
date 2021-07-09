package com.moqi.in20210709.group

import spock.lang.Specification

import java.util.function.Function
import java.util.stream.Collectors

/**
 * https://www.baeldung.com/java-groupingby-count
 *
 * @author moqi* On 2021/7/9 01:12
 */
class StreamGroupingByCollectorUnitTest extends Specification {

    def "counting should work demo1"() {
        given:
        def list = ["Foo", "Bar", "Bar", "Bar", "Foo"]

        when:
        def map = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))

        then:
        2L == map.get("Foo")
        3L == map.get("Bar")
    }

    // groupingByConcurrent will use concurrentHashMap
    def "counting should work demo2"() {
        given:
        def list = ["Adam", "Bill", "Jack", "Joe", "Ian"]

        when:
        def map = list.stream().collect(Collectors.groupingByConcurrent(
                String::length, Collectors.counting()))

        then:
        2L == map.get(3)
        3L == map.get(4)
    }

}
