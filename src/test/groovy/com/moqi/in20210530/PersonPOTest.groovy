package com.moqi.in20210530

import spock.lang.Specification

class PersonPOTest extends Specification {

    def "builder: person page num should equals 1"() {
        when:
        def personPo = PersonPO.builder().name("person").build()

        then:
        personPo.getPageNum() == 1
    }

    def "super builder: person2 page num should equals 0"() {
        when:
        def personPo = Person2PO.builder().name("person").build()

        then:
        personPo.getPageNum() == 0
    }

    def "super builder: person2 page num should equals 1"() {
        when:
        def personPo = new Person2PO().toBuilder().name("person").build()

        then:
        personPo.getPageNum() == 1
    }

}
