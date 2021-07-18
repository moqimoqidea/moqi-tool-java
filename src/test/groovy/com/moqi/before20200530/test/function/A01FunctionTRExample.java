package com.moqi.before20200530.test.function;

import com.moqi.before20200530.bean.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Java 8 code example showing usage of Function.apply() method
 *
 * @author moqi On 2/19/21 14:22
 */
public class A01FunctionTRExample {

    public static void main(String[] args) {
        // Function<Employee, String> funcEmpToString= (Employee e)-> {return e.getName();};
        Function<Employee, String> funcEmpToString = Employee::getName;

        List<Employee> employeeList =
                Arrays.asList(new Employee("Tom Jones", 45),
                        new Employee("Harry Major", 25),
                        new Employee("Ethan Hardy", 65),
                        new Employee("Nancy Smith", 15),
                        new Employee("Deborah Sprightly", 29));

        List<String> empNameList = convertEmpListToNamesList(employeeList, funcEmpToString);
        empNameList.forEach(System.out::println);
    }

    private static List<String> convertEmpListToNamesList(List<Employee> employeeList, Function<Employee, String> funcEmpToString) {
        List<String> empNameList = new ArrayList<>();

        for (Employee emp : employeeList) {
            empNameList.add(funcEmpToString.apply(emp));
        }

        return empNameList;
    }
}
