package com.example.quizappcomplete.Model;

public class instituteadd {
    String name,email,code;

    public instituteadd(String name, String email, String code) {
        this.name = name;
        this.email = email;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
