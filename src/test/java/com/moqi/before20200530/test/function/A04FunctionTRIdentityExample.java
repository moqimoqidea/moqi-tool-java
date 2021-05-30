package com.moqi.before20200530.test.function;

import com.moqi.before20200530.bean.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Java 8 code showing usage of static method Function.identity()
 *
 * @author moqi On 2/20/21 10:35
 */

public class A04FunctionTRIdentityExample {

    public static void main(String[] args) {
        List<Employee> employeeList =
                Arrays.asList(new Employee("Tom Jones", 45),
                        new Employee("Harry Major", 25),
                        new Employee("Ethan Hardy", 65),
                        new Employee("Nancy Smith", 15),
                        new Employee("Deborah Sprightly", 29));

        List<Employee> empNameListInitials = applyIdentityToEmpList(employeeList, Function.identity());
        empNameListInitials.forEach(System.out::println);
    }

    private static List<Employee> applyIdentityToEmpList(List<Employee> employeeList, Function<Employee, Employee> funcEmpToEmp) {
        List<Employee> empNameList = new ArrayList<>();

        for (Employee emp : employeeList) {
            empNameList.add(funcEmpToEmp.apply(emp));
        }

        return empNameList;
    }

}
