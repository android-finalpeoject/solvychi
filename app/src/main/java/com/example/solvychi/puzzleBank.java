package com.example.solvychi;

import java.util.ArrayList;
import java.util.List;

public class puzzleBank {

    public static List<puzzle> getPuzzle(){
        final List<puzzle> myQuizListObjects = new ArrayList<>();
        // create instance of myQuiz class as questions of the fourth level:
        final puzzle pz1 = new puzzle(new int[]{R.drawable.p12,R.drawable.p1,R.drawable.p11},R.drawable.p1,0,R.drawable.pz1,R.drawable.pzl1);
        final puzzle pz2 = new puzzle(new int[]{R.drawable.p21,R.drawable.p2,R.drawable.p22},R.drawable.p2,0,R.drawable.pz2,R.drawable.pzl2);
        final puzzle pz3 = new puzzle(new int[]{R.drawable.p32,R.drawable.p31,R.drawable.p3},R.drawable.p3,0,R.drawable.pz3,R.drawable.pzl3);
        final puzzle pz4 = new puzzle(new int[]{R.drawable.p4,R.drawable.p42,R.drawable.p41},R.drawable.p4,0,R.drawable.pz4,R.drawable.pzl4);
        myQuizListObjects.add(pz1);
        myQuizListObjects.add(pz2);
        myQuizListObjects.add(pz3);
        myQuizListObjects.add(pz4);

        return myQuizListObjects;
    }


}
