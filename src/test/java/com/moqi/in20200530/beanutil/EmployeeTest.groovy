package com.moqi.in20200530.beanutil

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Lists
import org.apache.commons.beanutils.BeanUtils
import org.apache.commons.beanutils.ConvertUtils
import org.apache.commons.beanutils.PropertyUtils
import org.apache.commons.beanutils.converters.DateConverter
import spock.lang.Specification
import spock.lang.Unroll

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * 测试 BeanUtils 功能
 *
 * @author moqi* On 2021/5/30 18:23
 */
class EmployeeTest extends Specification {

    def "property utils simple should work"() {
        given:
        def employee = Employee.builder().firstName("first").build()

        when:
        def firstName = PropertyUtils.getSimpleProperty(employee, "firstName")
        def lastName = PropertyUtils.getSimpleProperty(employee, "lastName")
        PropertyUtils.setSimpleProperty(employee, "firstName", "someNewFirstName")
        PropertyUtils.setSimpleProperty(employee, "lastName", "lastName")

        then:
        firstName == "first"
        lastName == null
        employee.firstName == "someNewFirstName"
        employee.lastName == "lastName"
    }

    def "property utils indexed should work"() {
        given:
        def employee = Employee.builder()
                .subordinate(Lists.newArrayList(
                        Employee.builder().lastName("sub1LastName").build(),
                        Employee.builder().firstName("sub2FirstName").build()
                ))
                .build()

        when:
        def index = 0
        def name = "subordinate[" + index + "]"
        def employeeS1 = (Employee) PropertyUtils.getIndexedProperty(employee, name)
        def employeeS2 = (Employee) PropertyUtils.getIndexedProperty(employee, "subordinate", 1)
        PropertyUtils.setSimpleProperty(employee, "subordinate", Lists.newArrayList(
                Employee.builder().build(),
                Employee.builder().build(),
                Employee.builder().build(),
                Employee.builder().build()
        ))
        PropertyUtils.setIndexedProperty(employee, "subordinate[2]",
                Employee.builder().firstName("sub3FirstName").build()
        )
        PropertyUtils.setIndexedProperty(employee, "subordinate", 3,
                Employee.builder().firstName("sub4FirstName").build()
        )

        then:
        employeeS1.lastName == "sub1LastName"
        employeeS2.firstName == "sub2FirstName"
        employee.subordinate.size() == 4
        employee.subordinate[2].getFirstName() == "sub3FirstName"
        employee.subordinate[3].getFirstName() == "sub4FirstName"
    }

    def "property utils mapped should work"() {
        given:
        def employee = Employee.builder()
                .address(new HashMap<String, Employee.Address>() {
                    {
                        put("home", Employee.Address.builder().name("home").build())
                    }
                })
                .build()

        when:
        def homeAddress = PropertyUtils.getMappedProperty(employee, "address(home)")
        def homeAddress2 = PropertyUtils.getMappedProperty(employee, "address", "home")
        PropertyUtils.setMappedProperty(employee, "address", "company",
                Employee.Address.builder().name("company").build()
        )
        PropertyUtils.setMappedProperty(employee, "address(company123)",
                Employee.Address.builder().name("company123").build()
        )

        then:
        homeAddress == Employee.Address.builder().name("home").build()
        homeAddress2 == homeAddress
        employee.address.size() == 3
        employee.address["company"] == Employee.Address.builder().name("company").build()
        employee.address["company123"] == Employee.Address.builder().name("company123").build()
    }

    def "property utils mapped replace map field should work"() {
        given:
        def employee = Employee.builder()
                .address(Collections.singletonMap("home", Employee.Address.builder().name("home").build()))
                .build()

        when:
        PropertyUtils.setSimpleProperty(employee, "address",
                Collections.singletonMap("company", Employee.Address.builder().name("company").build())
        )

        then:
        employee.address.size() == 1
        employee.address["company"] == Employee.Address.builder().name("company").build()
    }

    def "property utils nested should work"() {
        given:
        def employee = Employee.builder()
                .address(Collections.singletonMap("home", Employee.Address.builder().name("New York").build()))
                .build()

        when:
        def homeName = PropertyUtils.getNestedProperty(employee, "address(home).name",)
        PropertyUtils.setNestedProperty(employee, "address(home).name", "Los Angeles")

        then:
        homeName == "New York"
        employee.getAddress().get("home").name == "Los Angeles"
    }

    def "property utils data type conversions should throw exception"() {
        given:
        def employee = Employee.builder().build()

        when:
        PropertyUtils.setProperty(employee, "firstName", 123)

        then:
        thrown(IllegalArgumentException)
    }

    def "bean utils data type conversions should work"() {
        given:
        def employee = Employee.builder().build()

        when:
        BeanUtils.setProperty(employee, "firstName", 123)
        BeanUtils.setProperty(employee, "age", "18")

        then:
        employee.firstName == "123"
        employee.age == 18
    }

    def "bean utils data type conversions populate should work"() {
        given:
        def employee = Employee.builder().build()

        when:
        BeanUtils.populate(employee, ImmutableMap.of("firstName", 123, "age", "18"))

        then:
        employee.firstName == "123"
        employee.age == 18
    }

    def "bean utils data type conversions user define converter should work"() {
        given:
        def employee = Employee.builder().build()
        ThreadLocal<DateFormat> DATE_YEAR_MONTH_DAY_FORMATTER = ThreadLocal
                .withInitial({ -> new SimpleDateFormat("yyyyMMdd") })

        when:
        DateConverter converter = new DateConverter(null)
        converter.setPattern("yyyyMMdd")
        ConvertUtils.register(converter, Date.class)
        BeanUtils.setProperty(employee, "date", "20140407")

        then:
        employee.date == DATE_YEAR_MONTH_DAY_FORMATTER.get().parse("20140407")
    }

    def "bean utils copy properties type mismatch should throw exception"() {
        given:
        def employee = Employee.builder()
                .firstName("firstName")
                .lastName("lastName")
                .age(10)
                .date(new Date())
                .build()

        when:
        def copyEmployee = CopyEmployee.builder().build()
        BeanUtils.copyProperties(copyEmployee, employee)

        then:
        thrown(IllegalArgumentException)
    }

    @Unroll
    def "copy properties after register employeeAge #employeeAge then ageEnumValue #ageEnumValue"() {
        given:
        def employee = Employee.builder()
                .firstName("firstName")
                .lastName("lastName")
                .age(employeeAge)
                .date(new Date())
                .build()

        when:
        ConvertUtils.register(new AgeEnumConverter(), AgeEnum.class)
        def copyEmployee = CopyEmployee.builder().build()
        BeanUtils.copyProperties(copyEmployee, employee)

        then:
        copyEmployee.age == ageEnumValue

        where:
        employeeAge | ageEnumValue
        null        | null
        33          | null
        0           | AgeEnum.ZERO
        10          | AgeEnum.TEN
        18          | AgeEnum.EIGHTEEN
        20          | AgeEnum.TWENTY
    }

}
