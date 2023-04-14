package edu.wctc.part4;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    private List<Employee> managedEmployees = new ArrayList<>();

    public Manager(String firstName, String lastName, String ssn) {
        super(firstName, lastName, ssn);
    }

    public void hireEmployee(Employee employee, String cubeId) {
        getCompany().addEmployee(this, employee);
        managedEmployees.add(employee);
        employee.setCompany(getCompany());
        employee.doFirstTimeOrientation(cubeId);
    }

    public List<Employee> getEmployees() {
        return managedEmployees;
    }

    public void getReports() {
        for(Employee e : managedEmployees) {
            e.printReport();
        }
    }
}
