package com.example.quizappcomplete.Model;

public class Result {
    String total,correct,wrong,name,email;

    public Result(String total, String correct, String wrong,String name,String email) {
        this.total = total;
        this.correct = correct;
        this.wrong = wrong;
        this.name=name;
        this.email=email;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
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
}
