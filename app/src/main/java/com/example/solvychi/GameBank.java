package com.example.solvychi;

import java.util.ArrayList;
import java.util.List;

public class GameBank {
    private static List<GameObject> Level3Quiz(){
        final List<GameObject> myQuizListObjects = new ArrayList<>();
        // create instance of myQuiz class as questions of the third level:
        final GameObject quiz1 = new GameObject(new String[]{"B", "A", "C", "E", "S", "H"},"SEA","",3,R.drawable.q1);
        final GameObject quiz2 = new GameObject(new String[]{"A", "E", "M", "C", "P", "S"},"SPACE","",5,R.drawable.q2);
        final GameObject quiz3 = new GameObject(new String[]{"A", "T", "R", "O", "F", "M"},"FARM","",4,R.drawable.q3);
        final GameObject quiz4 = new GameObject(new String[]{"B", "I", "R", "E", "D", "A"},"IDEA","",4,R.drawable.q4);
        myQuizListObjects.add(quiz1);
        myQuizListObjects.add(quiz2);
        myQuizListObjects.add(quiz3);
        myQuizListObjects.add(quiz4);
        return myQuizListObjects;
    }
    private static List<GameObject> Level4Quiz(){
        final List<GameObject> myQuizListObjects = new ArrayList<>();
        // create instance of myQuiz class as questions of the third level:
        final GameObject quiz1 = new GameObject(new String[]{"B", "A", "C", "E", "S", "H"},"SEA","",3,R.drawable.q1);
        final GameObject quiz2 = new GameObject(new String[]{"A", "E", "M", "C", "P", "S"},"SPACE","",5,R.drawable.q2);
        final GameObject quiz3 = new GameObject(new String[]{"A", "T", "R", "O", "F", "M"},"FARM","",4,R.drawable.q3);
        final GameObject quiz4 = new GameObject(new String[]{"B", "I", "R", "E", "D", "A"},"IDEA","",4,R.drawable.q4);
        myQuizListObjects.add(quiz1);
        myQuizListObjects.add(quiz2);
        myQuizListObjects.add(quiz3);
        myQuizListObjects.add(quiz4);
        return myQuizListObjects;
    }
    public static List<GameObject> getQuiz(String selectedLevelName) {
        switch (selectedLevelName) {

            case "level3":
                return Level3Quiz();
           case "level4":
              return Level4Quiz();
            default:
                return Level4Quiz();

        }

    }
}
