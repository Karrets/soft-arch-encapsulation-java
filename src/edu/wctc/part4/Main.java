package edu.wctc.part4;

/**
 * This class is the startup class for the program. But together with the other
 * classes provided it is not a particularly good simulation of the real world.
 * Employees don't just hire themselves and them tell themselves to go through
 * orientation. There's usually a company and a HR person involved.
 *
 * Refer to the Employee class for further directions.
 */
public class Main {

    public static void main(String[] args) {
        Manager steve = new Manager("Steve", "Bundy", "000-00-0002");
        Company c = new Company(steve);

        steve.hireEmployee(new Employee("Jane", "Doe", "123-12-1234"), "CUBEID");
        steve.hireEmployee(new Employee("John", "Doe", "123-12-1235"), "CUBEID");

        Manager marty = new Manager("Manager", "Marty", "123-45-6789");
        Manager manny = new Manager("Manager", "Manny", "123-45-6790");

        steve.hireEmployee(marty, "CUBEID");
        marty.hireEmployee(manny, "CUBE-r ID-er");

        c.companyReport();
    }

}
