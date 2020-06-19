package com.example.quizappcomplete.Model;

public class Response {

    String answer, userAnswer, questionNumber;

    public Response(String answer, String userAnswer, String questionNumber) {
        this.answer = answer;
        this.userAnswer = userAnswer;
        this.questionNumber = questionNumber;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public boolean isAswerCorrect(){
        if(userAnswer.equals (answer))
            return true;
        return false;
    }
}
