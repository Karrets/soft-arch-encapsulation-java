package edu.wctc.part2;


import com.sun.jdi.request.DuplicateRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * In this lab focus on METHOD encapsulation and fix/add code as necessary.
 * Pay special attention to the following issues:
 * <p>
 * 1. Not all methods need to be public
 * <p>
 * 2. When methods need to be called in a certain order you can do this
 * by creating a parent method that calls the other, helper methods.
 * <p>
 * 3. There is some duplicate code used that violates the D.R.Y. principle.
 * Eliminate that by encapsulating the duplicate code in a new method
 * and then call that method in place of the duplicate code.
 * <p>
 * 4. All method parameters should be validated (except booleans).
 * <p>
 * Review the tips in the document Encapsulation Checklist if needed.
 */
public class Employee {
    private String firstName;
    private String lastName;
    private String ssn;
    private boolean metWithHr;
    private boolean metDeptStaff;
    private boolean reviewedDeptPolicies;
    private boolean movedIn;
    private String cubeId;
    private LocalDate orientationDate;
    private LocalDate latestPolicyReview;
    private LocalDate latestCubicleMove;

    public Employee(String firstName, String lastName, String ssn) {
        setFirstName(firstName);
        setLastName(lastName);
        setSsn(ssn);
    }

    public void performOrientation(String cubeId) {
        if(orientationDate != null)
            throw new DuplicateRequestException("Employee already orientated. If you need to review dept. policy or change cubeId, see other public methods.");

        meetWithHrForBenefitAndSalaryInfo();
        meetDepartmentStaff();

        reviewDeptPolicies();
        moveIntoCubicle(cubeId);

        setOrientationDate(
                LocalDate.now()
        );
    }

    // Assume this must be performed first, and assume that an employee
    // would only do this once, upon being hired.
    private void meetWithHrForBenefitAndSalaryInfo() {
        metWithHr = true;
        System.out.println(doActionOnTime("met with HR on", orientationDate));
    }

    // Assume this must be performed second, and assume that an employee
    // would only do this once, upon being hired.
    private void meetDepartmentStaff() {
        metDeptStaff = true;
        System.out.println(doActionOnTime("met with dept staff on", orientationDate));
    }

    // Assume this must be performed third. And assume that because department
    // policies may change that this method may need to be called 
    // independently of other classes.
    public void reviewDeptPolicies() {
        reviewedDeptPolicies = true;
        setLatestPolicyReview(LocalDate.now());
        System.out.println(doActionOnTime("reviewed dept policies on", latestPolicyReview));
    }

    // Assume this must be performed fourth. And assume that because employees
    // sometimes change office locations that this method may need to be called 
    // independently of other classes.
    public void moveIntoCubicle(String cubeId) {
        this.cubeId = cubeId;
        this.movedIn = true;
        setLatestCubicleMove(LocalDate.now());
        System.out.println(doActionOnTime("moved into cubicle", latestCubicleMove));
    }

    private String doActionOnTime(String action, LocalDate time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
        String fmtDate = formatter.format(time);

        return (String.format("%s %s %s %s on %s", firstName, lastName, action, cubeId, fmtDate));
    }

    // setter methods give the developer the power to control what data is
    // allowed through validation.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.length() < 1)
            throw new IllegalArgumentException("First name must be at least one character.");

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.length() < 1)
            throw new IllegalArgumentException("Last name must contain at least one character.");

        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        if(ssn == null || ssn.length() == 9 || ssn.length() == 11) //9 characters for ssn without hyphens, 11 for those with.
            throw new IllegalArgumentException("SSN must be either 9 or 11 characters. (May or may not include dashes).");

        this.ssn = ssn;
    }

    public boolean hasMetWithHr() {
        return metWithHr;
    }

    public boolean hasMetDeptStaff() {
        return metDeptStaff;
    }

    public boolean hasReviewedDeptPolicies() {
        return reviewedDeptPolicies;
    }

    public boolean hasMovedIn() {
        return movedIn;
    }

    public String getCubeId() {
        return cubeId;
    }

    public void setCubeId(String cubeId) {
        if(cubeId == null)
            throw new IllegalArgumentException("cubeId may not be null.");
        this.cubeId = cubeId;
    }

    public LocalDate getOrientationDate() {
        return orientationDate;
    }

    private void setOrientationDate(LocalDate orientationDate) {
        this.orientationDate = orientationDate;
    }

    public LocalDate getLatestPolicyReview() {
        return latestPolicyReview;
    }

    public void setLatestPolicyReview(LocalDate latestPolicyReview) {
        if(latestPolicyReview.isBefore(this.latestPolicyReview))
            throw new IllegalArgumentException("New latest policy review must be sooner than previous.");

        this.latestPolicyReview = latestPolicyReview;
    }

    public LocalDate getLatestCubicleMove() {
        return latestCubicleMove;
    }

    public void setLatestCubicleMove(LocalDate latestCubicleMove) {
        if(latestCubicleMove.isBefore(this.latestCubicleMove))
            throw new IllegalArgumentException("New latest cubicle move must be sooner than previous.");

        this.latestCubicleMove = latestCubicleMove;
    }
}
