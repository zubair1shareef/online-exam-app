package com.example.quizappcomplete.Model;

import java.io.Serializable;

public class QuizInfo implements Serializable {

    String title, date, time, duration, branch, semester, marksperquestion, totalMarks, instituteCode, createdBy, createdByUid;

    public QuizInfo(String title, String date, String time, String duration, String branch, String semester, String marksperquestion, String totalMarks, String instituteCode, String createdBy, String createdByUid) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.branch = branch;
        this.semester = semester;
        this.marksperquestion = marksperquestion;
        this.totalMarks = totalMarks;
        this.instituteCode = instituteCode;
        this.createdBy = createdBy;
        this.createdByUid = createdByUid;
    }

    public QuizInfo(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMarksperquestion() {
        return marksperquestion;
    }

    public void setMarksperquestion(String marksperquestion) {
        this.marksperquestion = marksperquestion;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getInstituteCode() {
        return instituteCode;
    }

    public void setInstituteCode(String instituteCode) {
        this.instituteCode = instituteCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByUid() {
        return createdByUid;
    }

    public void setCreatedByUid(String createdByUid) {
        this.createdByUid = createdByUid;
    }
}

