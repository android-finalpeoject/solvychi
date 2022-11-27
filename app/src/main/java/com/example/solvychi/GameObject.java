package com.example.solvychi;

public class GameObject {
    private String [] keys;
    private String correctAnswer, userAns;
    private int maxCounter , quizImage;

    public GameObject(String [] keys, String correctAnswer, String userAns, int maxCounter, int quizImage)
    {
        this.keys = keys;
        this.correctAnswer = correctAnswer;
        this.userAns = userAns;
        this.maxCounter = maxCounter;
        this.quizImage = quizImage;
    }
    //===============GETERS==================
    public String[] getKeys(){ return keys;}
    public String getCorrectAnswer(){  return correctAnswer;}
    public String getUserAns(){ return userAns;}
    public int getMaxCounter(){ return maxCounter;}
    public int getQuizImage(){ return  quizImage;}
    //==============SETERS===================
    public void setKeys(String [] keys){ this.keys = keys;}
    public void setCorrectAnswer(String correct){  correctAnswer = correct;}
    public void setUserAns(String userAn){ userAns = userAn;}
    public void setMaxCounter(int max){ maxCounter = max;}
    public void setQuizImage(int image){ quizImage = image;}
}
