package com.itexico.instrumentationtesting.espresso.database;

import com.itexico.instrumentationtesting.espresso.utils.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by iTexico Developer on 8/24/2016.
 */
public class Data {

    public static final List<Employee> EMPLOYEE_LIST = new ArrayList<>(Arrays.asList(
            new Employee(22222, "John"),
            new Employee(87666, "David"),
            new Employee(53234, "Allejandro"),
            new Employee(87765, "Miguel"),
            new Employee(99999, "Harry"),
            new Employee(45443, "Nickole"),
            new Employee(78877, "Jachson"),
            new Employee(78877, "Anderson"),
            new Employee(78877, "Joghnthon"),
            new Employee(78877, "Peter")
    ));

    public static final List<Employee> HR_EMPLOYEE_LIST = new ArrayList<>(Arrays.asList(
            new Employee(22222, "Sophia"),
            new Employee(87666, "Emma"),
            new Employee(53234, "Olivia"),
            new Employee(87765, "Ava"),
            new Employee(99999, "Mia"),
            new Employee(45443, "Isabella"),
            new Employee(78877, "Zoe"),
            new Employee(78877, "Lily")
    ));



}
