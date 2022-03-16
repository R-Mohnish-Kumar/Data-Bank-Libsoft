package com.mkr.aameclibrary.Staff.ui;

public class StaffDetails {
    String DOB;
    String Name;
    String Email;
    Long staffId;
    String Designation;

    StaffDetails(){

    }

    public StaffDetails(String DOB, String Name, String Email, Long staffId, String Designation) {
        this.DOB = DOB;
        this.Name=Name;
        this.Email = Email;
        this.staffId = staffId;
        this.Designation = Designation;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }
}
