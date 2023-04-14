package edu.wctc.part4;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Manager ceo;
    private List<Manager> managers = new ArrayList<>(); //Subset of employees. Only those who are managers,
    private List<Employee> employees = new ArrayList<>(); //All employees.

    public Company(Manager ceo) {
        ceo.setCompany(this);
        setCeo(ceo);
    }

    public void addEmployee(Manager hiringManager, Employee employee) {
        if(hiringManager == null)
            throw new IllegalArgumentException("Invalid hiring manager!");

        if(employee instanceof Manager manager)
            managers.add(manager);

        employees.add(employee);
    }

    public void companyReport() {
        for(Manager manager : managers)
            manager.getReports();
    }

    public Manager getCeo() {
        return ceo;
    }

    public void setCeo(Manager ceo) {
        if(ceo == null)
            throw new IllegalArgumentException("CEO may not be null");
        this.ceo = ceo;
    }
}
