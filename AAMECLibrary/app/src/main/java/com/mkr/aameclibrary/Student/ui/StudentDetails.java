package com.mkr.aameclibrary.Student.ui;

public class StudentDetails {
    String DOB;
    String Department;
    String Name;
    Long Regno;
    String intern;

    StudentDetails(){

    }

    public StudentDetails(String DOB, String department, String name, Long regno, String intern) {
        this.DOB = DOB;
        Department = department;
        Name = name;
        Regno = regno;
        this.intern = intern;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getRegno() {
        return Regno;
    }

    public void setRegno(Long regno) {
        Regno = regno;
    }

    public String getIntern() {
        return intern;
    }

    public void setIntern(String intern) {
        this.intern = intern;
    }
}
