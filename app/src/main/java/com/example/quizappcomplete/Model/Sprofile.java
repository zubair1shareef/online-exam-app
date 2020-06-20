package com.example.quizappcomplete.Model;

public class Sprofile {
    String branch,email,instituteCode,name,role,semester;

    public Sprofile(String branch, String email, String instituteCode, String name, String role, String semester) {
        this.branch = branch;
        this.email = email;
        this.instituteCode = instituteCode;
        this.name = name;
        this.role = role;
        this.semester = semester;
    }

    public Sprofile() {

    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstituteCode() {
        return instituteCode;
    }

    public void setInstituteCode(String instituteCode) {
        this.instituteCode = instituteCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
