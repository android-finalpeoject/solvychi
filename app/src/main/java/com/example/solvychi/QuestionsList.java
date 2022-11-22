package com.example.solvychi;

public class QuestionsList {
    private String texte;
    private String texte2;
    private int image;
    private int background;

    public QuestionsList(String texte, String texte2, int image, int background) {
        this.texte = texte;
        this.texte2 = texte2;
        this.image = image;
        this.background = background;
    }


    public String getTexte() {
        return texte;
    }

    public int getImage() {
        return image;
    }

    public String getTexte2() {
        return texte2;
    }

    public int getBackground() {
        return background;
    }
}
