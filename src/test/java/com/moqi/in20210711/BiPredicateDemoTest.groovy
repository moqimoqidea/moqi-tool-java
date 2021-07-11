package com.moqi.in20210711


import spock.lang.Specification

import java.util.function.BiPredicate
import java.util.stream.Collectors

/**
 * Java 8 BiPredicate Examples:
 * https://mkyong.com/java8/java-8-bipredicate-examples/
 *
 * @author moqi* On 2021/7/11 16:05
 */
class BiPredicateDemoTest extends Specification {

    def "test when a string length is right then return true else false"() {
        given:
        BiPredicate<String, Integer> filter = (x, y) -> x.length() == y

        expect:
        result == filter.test(string, integer)

        where:
        result | string | integer
        true   | "abc"  | 3
        false  | "java" | 10
    }

    /**
     * Positional parameters
     * https://groovy-lang.org/objectorientation.html#_positional_parameters
     */
    def "test a domain should return right result"() {
        given:
        def domainList = Arrays.asList(new Domain("google.com", 1),
                new Domain("i-am-spammer.com", 10),
                new Domain("mkyong.com", 0),
                new Domain("microsoft.com", 2))

        expect:
        result == filterBadDomain(domainList, bi)

        where:
        result                                                     | bi
        [["google.com", 1] as Domain, ["mkyong.com", 0] as Domain] | (domain, score) -> (domain.equalsIgnoreCase("google.com") || score == 0)
        [["mkyong.com", 0] as Domain]                              | (domain, score) -> score == 0
        [["i-am-spammer.com", 10] as Domain]                       | (domain, score) -> domain.startsWith("i") && score > 5
    }

    def "test a domain with or syntax should return right result"() {
        given:
        def domainList = Arrays.asList(new Domain("google.com", 1),
                new Domain("i-am-spammer.com", 10),
                new Domain("mkyong.com", 0),
                new Domain("microsoft.com", 2))
        BiPredicate<String, Integer> bi = (domain, score) -> (domain.equalsIgnoreCase("google.com") || score == 0)

        when:
        def result = filterBadDomain(domainList, bi | (domain, x) -> domain.equalsIgnoreCase("MICROSOFT.COM"))

        then:
        result == [["google.com", 1] as Domain, ["mkyong.com", 0] as Domain, ["microsoft.com", 2] as Domain]
    }

    static <T extends Domain> List<T> filterBadDomain(
            List<T> list, BiPredicate<String, Integer> biPredicate
    ) {
        return list.stream()
                .filter(x -> biPredicate.test(x.name, x.score))
                .collect(Collectors.toList())
    }

}

class Domain {
    String name
    Integer score

    Domain(String name, Integer score) {
        this.name = name
        this.score = score
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Domain domain = (Domain) o

        if (name != domain.name) return false
        if (score != domain.score) return false

        return true
    }

    int hashCode() {
        int result
        result = (name != null ? name.hashCode() : 0)
        result = 31 * result + (score != null ? score.hashCode() : 0)
        return result
    }

    @Override
    String toString() {
        return "Domain{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}'
    }
}
