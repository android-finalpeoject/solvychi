package com.example.solvychi;

public class BoarderList {
    private String texte;
    private String texte2;
    private int image;
    private int background;
    private int steps;



    public BoarderList(String texte, String texte2, int image, int background, int steps) {
        this.texte = texte;
        this.texte2 = texte2;
        this.image = image;
        this.background = background;
        this.steps = steps;
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

    public int getSteps() {
        return steps;
    }
}
