package com.moqi.test.function;

import com.moqi.bean.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Java 8 code showing usage of default method Function.andThen()
 *
 * @author moqi On 2/20/21 10:18
 */

public class A02FunctionTRAndThenExample {

    public static void main(String[] args) {
        // Function<Employee, String> funcEmpToString= (Employee e)-> {return e.getName();};
        Function<Employee, String> funcEmpToString = Employee::getName;

        List<Employee> employeeList =
                Arrays.asList(new Employee("Tom Jones", 45),
                        new Employee("Harry Major", 25),
                        new Employee("Ethan Hardy", 65),
                        new Employee("Nancy Smith", 15),
                        new Employee("Deborah Sprightly", 29));

        Function<String, String> initialFunction = (String s) -> s.substring(0, 1);
        List<String> empNameListInitials = convertEmpListToNamesList(employeeList, funcEmpToString.andThen(initialFunction));

        // empNameListInitials.forEach(str -> { System.out.print(" " + str); });
        empNameListInitials.forEach(str -> System.out.print(" " + str));
    }

    private static List<String> convertEmpListToNamesList(List<Employee> employeeList, Function<Employee, String> funcEmpToString) {
        List<String> empNameList = new ArrayList<>();

        for (Employee emp : employeeList) {
            empNameList.add(funcEmpToString.apply(emp));
        }

        return empNameList;
    }

}
