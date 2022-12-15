package com.example.solvychi;

public class puzzle {

    private int  pieces[], correctAnswer, userAns, quizImage,completed;

    public puzzle(int[] pieces,int correctAnswer,int userAns,int quizImage, int completed) {
        this.pieces = pieces;
        this.correctAnswer = correctAnswer;
        this.userAns = userAns;
        this.quizImage = quizImage;
        this.completed = completed;
    }

    public int[] getPieces() {
        return pieces;
    }

    public void setPieces(int[] pieces) {
        this.pieces = pieces;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getUserAns() {
        return userAns;
    }

    public void setUserAns(int userAns) {
        this.userAns = userAns;
    }

    public int getQuizImage() {
        return quizImage;
    }

    public void setQuizImage(int quizImage) {
        this.quizImage = quizImage;
    }
}
