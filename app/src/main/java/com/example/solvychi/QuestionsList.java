package com.example.solvychi;

public class QuestionsList {
    private String texte;
    private int image;

    public QuestionsList(String texte , int image) {
        this.texte =texte;
        this.image=image;
    }



    public String getTexte() {
        return texte;
    }

    public int getImage() {
        return image;
    }
}
