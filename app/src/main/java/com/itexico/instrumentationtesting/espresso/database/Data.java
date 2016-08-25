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
            new Employee(22222, "Emp_1"),
            new Employee(87666, "Emp_2"),
            new Employee(53234, "Emp_3"),
            new Employee(87765, "Emp_4"),
            new Employee(99999, "Emp_5"),
            new Employee(45443, "Emp_6"),
            new Employee(78877, "Emp_7"),
            new Employee(78877, "Emp_8"),
            new Employee(78877, "Emp_9"),
            new Employee(78877, "Emp_10")
    ));
}
