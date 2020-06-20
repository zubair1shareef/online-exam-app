package com.example.quizappcomplete.Model;

import java.io.Serializable;

public class Result implements Serializable {
    String totalQues,correct,wrong,name,email,marksObtained, totalMarks;

    public Result(String total, String correct, String wrong, String name, String email, String marksObtained, String totalMarks) {
        this.totalQues = total;
        this.correct = correct;
        this.wrong = wrong;
        this.name = name;
        this.email = email;
        this.marksObtained = marksObtained;
        this.totalMarks = totalMarks;
    }

    public Result(){}

    public String getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(String marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getTotalQues() {
        return totalQues;
    }

    public void setTotalQues(String total) {
        this.totalQues = total;
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
