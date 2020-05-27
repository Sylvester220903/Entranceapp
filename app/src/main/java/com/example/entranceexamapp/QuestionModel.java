package com.example.entranceexamapp;

class QuestionModel {
    String question, optionA, optionB, optionC, optionD;
    String answer;

    QuestionModel( String question, String optionA, String optionB, String optionC, String optionD, String answer){
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
